package com.jljcxy.common.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import com.google.common.cache.Cache;
import com.jljcxy.common.base.key.IdGenerator;
import com.jljcxy.common.base.key.IdType;
import com.jljcxy.common.base.utils.Const;
import com.jljcxy.common.base.utils.FieldUtil;
import com.jljcxy.common.base.utils.ReflectUtil;
import com.jljcxy.common.base.utils.Sql;
import com.jljcxy.common.base.utils.Tools;
import com.jljcxy.manage.sys.common.LoginVO;
import com.jljcxy.manage.sys.tableref.TableRef;
import com.jljcxy.manage.sys.tableref.TableRefParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【数据访问】DAO基类(单表操作)
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */
@Slf4j
@Scope("prototype")
public class BaseDao<T> extends BaseJDBC {
	private final Class<T> clazz;
	private final List<Field> fields;
	private final String table;
	private final String idName;
	private final String idType;
	private Boolean includDr;
	protected static Boolean checkTableRef = false;// [检查表的引用关系]的开关

	@SuppressWarnings("unchecked")
	protected BaseDao() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.fields = ReflectUtil.fields(clazz);
		this.table = ReflectUtil.tableName(clazz);
		this.idName = ReflectUtil.idName(fields);
		this.idType = ReflectUtil.idType(fields);
		this.includDr = fields.stream().anyMatch(i -> i.getName().contains(Const.SQL.DR));
	}

	@Autowired
	private IdGenerator idGenerator;// 主键生成器

	/**
	 * 按条件修改记录
	 *
	 * @param t 记录对象
	 * @param c 查询条件
	 */
	public <C extends BaseCondition> int update(final T t, final C c) {
		return update(false, t, c);
	}

	/**
	 * 按条件修改记录
	 *
	 * @param showSql 显示SQL语句
	 * @param t       记录对象
	 * @param c       查询条件
	 */
	public <C extends BaseCondition> int update(final Boolean showSql, final T t, final C c) {
		FieldUtil.Update forUpdate = FieldUtil.forUpdate(fields, t, c, request);
		String sql = Sql.builder().update().table(table).set(forUpdate.getSql()).sql();
		if (showSql)
			log.info(Sql.parse(sql, forUpdate.getObj()));
		return update(sql, forUpdate.getObj());
	}

	/**
	 * 修改记录
	 *
	 * @param t 记录对象
	 */
	public int update(final T t) {
		return update(false, t);
	}

	/**
	 * 修改记录
	 *
	 * @param showSql 显示SQL语句
	 * @param t       记录对象
	 */
	public int update(final Boolean showSql, final T t) {
		FieldUtil.Update update = FieldUtil.forUpdate(fields, t, request);
		String sql = Sql.builder().update().table(table).set(update.getSql()).sql();
		if (showSql)
			log.info(Sql.parse(sql, update.getObj()));
		return update(sql, update.getObj());
	}

	/**
	 * 保存记录
	 *
	 * @param t 记录对象
	 */
	public T save(final T t) {
		return save(false, t);
	}

	/**
	 * 保存记录
	 *
	 * @param showSql 显示SQL语句
	 * @param t       记录对象
	 */
	public T save(final Boolean showSql, final T t) {
		if (IdType.SNOW.equals(idType) || IdType.UUID.equals(idType)) {
			Object id = IdType.SNOW.equals(idType) ? idGenerator.create() : Tools.uuid();
			FieldUtil.Insert<T> insert = FieldUtil.snowInsert(fields, t, id, idName, request);
			String sql = Sql.builder().insert().into().table(table).values(insert.getSql()).sql();
			save(showSql, insert.getT(), sql, fields);
			return insert.getT();
		} else {
			FieldUtil.Insert<T> obj = FieldUtil.autoInsert(fields, t, request);
			String sql = Sql.builder().insert().into().table(table).values(obj.getSql()).sql();
			return saveKey(showSql, obj.getT(), sql, idName, fields);
		}
	}

	/**
	 * 查询记录个数
	 *
	 * @param c 查询条件
	 */
	public <C extends BaseCondition> int count(final C c) {
		return count(false, c);
	}

	/**
	 * 查询分页列表
	 *
	 * @param showSql 显示SQL语句
	 * @param c       查询条件
	 */
	public <C extends BaseCondition> int count(final Boolean showSql, final C c) {
		String sql = Sql.builder().select().count().from().table(table).as().where(c).sql();
		return count(showSql, sql, c);
	}

	/**
	 * 查询分页列表
	 *
	 * @param c 查询条件
	 */
	public <C extends BaseCondition> Page<T> page(final C c) {
		return page(false, c, false);
	}

	/**
	 * 查询分页列表
	 *
	 * @param showSql 显示SQL语句
	 * @param c       查询条件
	 */
	public <C extends BaseCondition> Page<T> page(final Boolean showSql, final C c) {
		return page(showSql, c, false);
	}

	/**
	 * 分页列表
	 *
	 * @param c         查询条件
	 * @param nestCount 使用嵌套方式查询记录个数
	 */
	public <C extends BaseCondition> Page<T> page(final C c, final Boolean nestCount) {
		return page(false, c, nestCount);
	}

	/**
	 * 分页列表
	 *
	 * @param showSql   显示SQL语句
	 * @param c         查询条件
	 * @param nestCount 使用嵌套方式查询记录个数
	 */
	public <C extends BaseCondition> Page<T> page(final Boolean showSql, final C c, final Boolean nestCount) {
		String sql = Sql.builder().select().fields(fields).from().table(table).as().where(c).sql();
		return page(showSql, sql, c, clazz, nestCount);
	}

	/**
	 * 按主键查询单个记录
	 */
	public T findById(final Object obj) {
		return findById(false, obj);
	}

	/**
	 * 按主键查询单个记录
	 *
	 * @param showSql 显示SQL语句
	 * @param id      主键
	 */
	public T findById(final Boolean showSql, final Object id) {
		String sql = Sql.builder().select().fields(fields).from().table(table).as().whereT().id(idName).sql();
		return findById(showSql, sql, clazz, id);
	}

	/**
	 * 按条件查询列表
	 *
	 * @param c 条件
	 */
	public <C extends BaseCondition> List<T> list(final C c) {
		return list(false, c);
	}

	/**
	 * 按条件查询列表
	 *
	 * @param showSql 显示SQL语句
	 * @param c       条件
	 */
	public <C extends BaseCondition> List<T> list(Boolean showSql, final C c) {
		String sql = Sql.builder().select().fields(fields).from().table(table).as().where(c).sql();
		return list(showSql, sql, c, clazz);
	}

	/**
	 * 批量保存记录
	 *
	 * @param list 记录列表
	 */
	public List<T> replaceBatch(final List<T> list) {
		return saveBatch(false, list, Const.SQL.REPLACEINTO);
	}

	public List<T> replaceBatch(final Boolean showSql, final List<T> list) {
		return saveBatch(showSql, list, Const.SQL.REPLACEINTO);
	}

	public List<T> saveBatch(final List<T> list) {
		return saveBatch(false, list, Const.SQL.INSERTINTO);
	}

	public List<T> saveBatch(final Boolean showSql, final List<T> list) {
		return saveBatch(showSql, list, Const.SQL.INSERTINTO);
	}

	/**
	 * 批量保存记录
	 *
	 * @param showSql 显示SQL语句
	 * @param list    记录列表
	 */
	public List<T> saveBatch(final Boolean showSql, final List<T> list, String type) {
		StringBuilder sql = new StringBuilder();
		sql.append(type);
		sql.append(table);
		sql.append(Const.SQL.BLEFTBRACKET);
		sql.append(fields.stream().map(i -> Tools.toLine(i.getName())).collect(Collectors.joining(Const.SQL.COMMA)));
		sql.append(Const.SQL.RVALUES);
		sql.append(fields.stream().map(i -> Const.SQL.COLON + i.getName()).collect(Collectors.joining(Const.SQL.COMMA)));
		sql.append(Const.SQL.RIGHTBRACKET);
		for (T t : list) {
			for (Field i : fields) {
				try {
					i.setAccessible(true);
					if (Tools.toLine(i.getName()).equals(idName)) {
						if (IdType.UUID.equals(idType))
							i.set(t, Tools.uuid());
						if (IdType.SNOW.equals(idType))
							i.set(t, idGenerator.create());
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					log.error(Const.EXCEPTION_SAVE, e);
					throw new RuntimeException(Const.EXCEPTION_SAVE);
				}
			}
		}
		if (showSql)
			log.info(Sql.sqlBatch(sql.toString(), list, fields));
		batchOperate(list, sql.toString());
		return list;
	}

	/**
	 * 按主键数组删除记录
	 *
	 * @param ids 主键数组
	 */
	public int delete(final Object... ids) {
		return delete(false, ids);
	}

	/**
	 * 按主键数组删除记录
	 *
	 * @param showSql 显示SQL语句
	 * @param ids     主键数组
	 */
	public int delete(final Boolean showSql, final Object... ids) {
		if (checkTableRef) {
			if (showSql)
				log.info("正在执行数据完整性约束检查！");
			checkReference(new TableRefParam(table, ids), showSql);
		}
		String sql;
		if (includDr) {
			sql = Sql.builder().update().table(table).set(Const.SQL.DREQ).where().idIn(idName, ids).sql();
		} else {
			sql = Sql.builder().delete().from().table(table).where().idIn(idName, ids).sql();
		}
		return update(showSql, sql, ids);
	}

	/**
	 * 按条件删除记录
	 *
	 * @param c 查询条件
	 */
	public <C extends BaseCondition> int delete(final C c) {
		return delete(false, c);
	}

	/**
	 * 按条件删除记录
	 *
	 * @param showSql 显示SQL语句
	 * @param c       查询条件
	 */
	public <C extends BaseCondition> int delete(final Boolean showSql, final C c) {
		String sql = Sql.builder().delete().from().table(table).as().where(c).sql();
		return update(showSql, sql, c.array());
	}

	/**
	 * @方法说明 【表引用关系】列表
	 */
	public void checkReference(TableRefParam param, final Boolean showSql) {
		String sql = "SELECT t.main_table,t.main_info,t.sub_table,t.sub_info,t.ref_id,t.del_flag FROM sys_table_ref t WHERE t.dr=0 AND t.main_table=?";
		if (showSql)
			log.info(Sql.parse(sql, param.getTable()));
		List<TableRef> refs = list(sql, TableRef.class, param.getTable());
		for (TableRef ref : refs) {
			if (count(showSql, ref, param.getIds()) > 0)
				throw new RuntimeException("删除失败,在【" + ref.getSubInfo() + "】表中存【" + ref.getMainInfo() + "】的引用记录!");
		}
		if (showSql)
			log.info("数据完整性约束检查通过！");
	}

	public int count(final Boolean showSql, TableRef ref, Object[] ids) {
		List<Object> list = Arrays.asList(ids);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(1) FROM " + ref.getSubTable());
		sql.append(" WHERE " + ref.getRefId() + " IN" + Sql.toIn(ids));
		if (StringUtils.hasLength(ref.getDelFlag())) {
			sql.append(" AND " + ref.getDelFlag() + "=?");
			list.add(0);
		}
		return count(showSql, sql.toString(), list.toArray());
	}

	@Autowired
	private Cache<Long, LoginVO> userCache;
	@Autowired
	private HttpServletRequest request;

	public List<Long> dataAuth(String filedName) {
		Long userId = (Long) request.getAttribute("userId");
		LoginVO vo = userCache.getIfPresent(userId);
		return vo.getDeptIds();
	}
}
