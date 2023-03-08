package com.jljcxy.manage.base.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldr.common.base.Page;
/**
 * @类说明 【课程】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao; // 注入【课程】数据访问层

	/**
	 * @方法说明 【课程】新增并反回主键
	 */
	// @Transactional
	public Long save(Course course) {
		return courseDao.save(course).getId();
	}

	/**
	 * @方法说明 【课程】删除
	 */
	public int delete(Object[] ids) {
		return courseDao.delete(ids);
	}

	/**
	 * @方法说明 【课程】修改
	 */
	public int update(Course course) {
		return courseDao.update(course);
	}

	/**
	 * @方法说明 【课程】分页列表
	 */
	public Page<Course> page(CourseCond cond) {
		return courseDao.page(cond);
	}

	/**
	 * @方法说明 【课程】列表
	 */
	public List<Course> list(CourseCond cond) {
		return courseDao.list(cond);
	}

	/**
	 * @方法说明 【课程】按主键查
	 */
	public Course findById(Long id) {
		return courseDao.findById(id);
	}

	/**
	 * @方法说明 【课程】个数
	 */
	public int count(CourseCond cond) {
		return courseDao.count(cond);
	}
	/**
	 * @方法说明 【课程】批量保存与更新
	 */
	public void saveBatch(List<Course> courses) {
		courseDao.saveBatch(courses);
	}	
}