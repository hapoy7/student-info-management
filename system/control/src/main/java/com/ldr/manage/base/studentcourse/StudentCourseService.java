package com.jljcxy.manage.base.studentcourse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldr.common.base.Page;
/**
 * @类说明 【学生课程关系】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class StudentCourseService {

	@Autowired
	private StudentCourseDao studentCourseDao; // 注入【学生课程关系】数据访问层

	/**
	 * @方法说明 【学生课程关系】新增并反回主键
	 */
	// @Transactional
	public Long save(StudentCourse studentCourse) {
		return studentCourseDao.save(studentCourse).getId();
	}

	/**
	 * @方法说明 【学生课程关系】删除
	 */
	public int delete(Object[] ids) {
		return studentCourseDao.delete(ids);
	}

	/**
	 * @方法说明 【学生课程关系】修改
	 */
	public int update(StudentCourse studentCourse) {
		return studentCourseDao.update(studentCourse);
	}

	/**
	 * @方法说明 【学生课程关系】分页列表
	 */
	public Page<StudentCourse> page(StudentCourseCond cond) {
		return studentCourseDao.page(cond);
	}

	/**
	 * @方法说明 【学生课程关系】列表
	 */
	public List<StudentCourse> list(StudentCourseCond cond) {
		return studentCourseDao.list(cond);
	}

	/**
	 * @方法说明 【学生课程关系】按主键查
	 */
	public StudentCourse findById(Long id) {
		return studentCourseDao.findById(id);
	}

	/**
	 * @方法说明 【学生课程关系】个数
	 */
	public int count(StudentCourseCond cond) {
		return studentCourseDao.count(cond);
	}
	/**
	 * @方法说明 【学生课程关系】批量保存与更新
	 */
	public void saveBatch(List<StudentCourse> studentCourses) {
		studentCourseDao.saveBatch(studentCourses);
	}	
}