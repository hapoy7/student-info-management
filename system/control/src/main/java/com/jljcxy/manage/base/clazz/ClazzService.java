package com.jljcxy.manage.base.clazz;

import com.jljcxy.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @类说明 【班级】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class ClazzService {

	@Autowired
	private ClazzDao clazzDao; // 注入【班级】数据访问层

	/**
	 * @方法说明 【班级】新增并反回主键
	 */
	// @Transactional
	public Long save(Clazz clazz) {
		return clazzDao.save(clazz).getId();
	}

	/**
	 * @方法说明 【班级】删除
	 */
	public int delete(Object[] ids) {
		return clazzDao.delete(ids);
	}

	/**
	 * @方法说明 【班级】修改
	 */
	public int update(Clazz clazz) {
		return clazzDao.update(clazz);
	}

	/**
	 * @方法说明 【班级】分页列表
	 */
	public Page<Clazz> page(ClazzCond cond) {
		return clazzDao.page(cond);
	}

	/**
	 * @方法说明 【班级】列表
	 */
	public List<Clazz> list(ClazzCond cond) {
		return clazzDao.list(cond);
	}

	/**
	 * @方法说明 【班级】按主键查
	 */
	public Clazz findById(Long id) {
		return clazzDao.findById(id);
	}

	/**
	 * @方法说明 【班级】个数
	 */
	public int count(ClazzCond cond) {
		return clazzDao.count(cond);
	}
	/**
	 * @方法说明 【班级】批量保存与更新
	 */
	public void saveBatch(List<Clazz> clazzs) {
		clazzDao.saveBatch(clazzs);
	}
}
