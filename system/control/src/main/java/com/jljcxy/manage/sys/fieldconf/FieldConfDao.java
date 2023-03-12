package com.jljcxy.manage.sys.fieldconf;

import com.jljcxy.common.base.BaseDao;
import com.jljcxy.common.base.key.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @类说明 【字段配置】数据访问层
 * @author 高振中
 * @date 2023-02-02 15:51:45
 **/
//@Slf4j
@Repository
public class FieldConfDao extends BaseDao<FieldConf> {
	@Autowired
	private IdGenerator idGenerator;// 主键生成器

	/**
	 * @方法说明 【字段配置】新增
	 */
	public FieldConf save(FieldConf po) {
		po.setId(idGenerator.create());
		String sql = "REPLACE INTO sys_field_conf (id,user_id,component,cond,grid) VALUES (?,?,?,?,?)";
		Object[] param = { po.getId(), po.getUserId(), po.getComponent(), po.getCond(), po.getGrid() };
//		log.info(Sql.parse(sql.toString(), param));// 显示SQL语句
		update(sql, param);
		return po;
	}
}
