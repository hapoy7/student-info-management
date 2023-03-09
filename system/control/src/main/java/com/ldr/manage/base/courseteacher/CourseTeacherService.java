package com.ldr.manage.base.courseteacher;

import com.ldr.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @类说明 【老师课程关系】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class CourseTeacherService {

	@Autowired
	private CourseTeacherDao courseTeacherDao; // 注入【老师课程关系】数据访问层

	/**
	 * @方法说明 【老师课程关系】新增并反回主键
	 */
	// @Transactional
	public Long save(CourseTeacher courseTeacher) {
		return courseTeacherDao.save(courseTeacher).getId();
	}

	/**
	 * @方法说明 【老师课程关系】删除
	 */
	public int delete(Object[] ids) {
		return courseTeacherDao.delete(ids);
	}

	/**
	 * @方法说明 【老师课程关系】修改
	 */
	public int update(CourseTeacher courseTeacher) {
		return courseTeacherDao.update(courseTeacher);
	}

	/**
	 * @方法说明 【老师课程关系】分页列表
	 */
	public Page<CourseTeacher> page(CourseTeacherCond cond) {
		return courseTeacherDao.page(cond);
	}

	/**
	 * @方法说明 【老师课程关系】列表
	 */
	public List<CourseTeacher> list(CourseTeacherCond cond) {
		return courseTeacherDao.list(cond);
	}

	/**
	 * @方法说明 【老师课程关系】按主键查
	 */
	public CourseTeacher findById(Long id) {
		return courseTeacherDao.findById(id);
	}

	/**
	 * @方法说明 【老师课程关系】个数
	 */
	public int count(CourseTeacherCond cond) {
		return courseTeacherDao.count(cond);
	}
	/**
	 * @方法说明 【老师课程关系】批量保存与更新
	 */
	public void saveBatch(List<CourseTeacher> courseTeachers) {
		courseTeacherDao.saveBatch(courseTeachers);
	}
}
