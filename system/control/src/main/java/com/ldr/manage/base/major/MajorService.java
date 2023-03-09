package com.ldr.manage.base.major;

import com.ldr.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @类说明 【专业】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class MajorService {

	@Autowired
	private MajorDao majorDao; // 注入【专业】数据访问层

	/**
	 * @方法说明 【专业】新增并反回主键
	 */
	// @Transactional
	public Long save(Major major) {
		return majorDao.save(major).getId();
	}

	/**
	 * @方法说明 【专业】删除
	 */
	public int delete(Object[] ids) {
		return majorDao.delete(ids);
	}

	/**
	 * @方法说明 【专业】修改
	 */
	public int update(Major major) {
		return majorDao.update(major);
	}

	/**
	 * @方法说明 【专业】分页列表
	 */
	public Page<Major> page(MajorCond cond) {
		return majorDao.page(cond);
	}

	/**
	 * @方法说明 【专业】列表
	 */
	public List<Major> list(MajorCond cond) {
		return majorDao.list(cond);
	}

	/**
	 * @方法说明 【专业】按主键查
	 */
	public Major findById(Long id) {
		return majorDao.findById(id);
	}

	/**
	 * @方法说明 【专业】个数
	 */
	public int count(MajorCond cond) {
		return majorDao.count(cond);
	}
	/**
	 * @方法说明 【专业】批量保存与更新
	 */
	public void saveBatch(List<Major> majors) {
		majorDao.saveBatch(majors);
	}
}
