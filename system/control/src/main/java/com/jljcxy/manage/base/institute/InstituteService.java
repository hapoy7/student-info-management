package com.jljcxy.manage.base.institute;

import com.jljcxy.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @类说明 【学院】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class InstituteService {

	@Autowired
	private InstituteDao instituteDao; // 注入【学院】数据访问层

	/**
	 * @方法说明 【学院】新增并反回主键
	 */
	// @Transactional
	public Long save(Institute institute) {
		return instituteDao.save(institute).getId();
	}

	/**
	 * @方法说明 【学院】删除
	 */
	public int delete(Object[] ids) {
		return instituteDao.delete(ids);
	}

	/**
	 * @方法说明 【学院】修改
	 */
	public int update(Institute institute) {
		return instituteDao.update(institute);
	}

	/**
	 * @方法说明 【学院】分页列表
	 */
	public Page<Institute> page(InstituteCond cond) {
		return instituteDao.page(cond);
	}

	/**
	 * @方法说明 【学院】列表
	 */
	public List<Institute> list(InstituteCond cond) {
		return instituteDao.list(cond);
	}

	/**
	 * @方法说明 【学院】按主键查
	 */
	public Institute findById(Long id) {
		return instituteDao.findById(id);
	}

	/**
	 * @方法说明 【学院】个数
	 */
	public int count(InstituteCond cond) {
		return instituteDao.count(cond);
	}
	/**
	 * @方法说明 【学院】批量保存与更新
	 */
	public void saveBatch(List<Institute> institutes) {
		instituteDao.saveBatch(institutes);
	}
}
