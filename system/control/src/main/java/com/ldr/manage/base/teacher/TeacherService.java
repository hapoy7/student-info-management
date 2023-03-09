package com.ldr.manage.base.teacher;

import com.ldr.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @类说明 【老师】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class TeacherService {

	@Autowired
	private TeacherDao teacherDao; // 注入【老师】数据访问层

	/**
	 * @方法说明 【老师】新增并反回主键
	 */
	// @Transactional
	public Long save(Teacher teacher) {
		return teacherDao.save(teacher).getId();
	}

	/**
	 * @方法说明 【老师】删除
	 */
	public int delete(Object[] ids) {
		return teacherDao.delete(ids);
	}

	/**
	 * @方法说明 【老师】修改
	 */
	public int update(Teacher teacher) {
		return teacherDao.update(teacher);
	}

	/**
	 * @方法说明 【老师】分页列表
	 */
	public Page<Teacher> page(TeacherCond cond) {
		return teacherDao.page(cond);
	}

	/**
	 * @方法说明 【老师】列表
	 */
	public List<Teacher> list(TeacherCond cond) {
		return teacherDao.list(cond);
	}

	/**
	 * @方法说明 【老师】按主键查
	 */
	public Teacher findById(Long id) {
		return teacherDao.findById(id);
	}

	/**
	 * @方法说明 【老师】个数
	 */
	public int count(TeacherCond cond) {
		return teacherDao.count(cond);
	}
	/**
	 * @方法说明 【老师】批量保存与更新
	 */
	public void saveBatch(List<Teacher> teachers) {
		teacherDao.saveBatch(teachers);
	}
}
