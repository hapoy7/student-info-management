package com.jljcxy.manage.base.student;

import com.jljcxy.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @类说明 【学生】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao; // 注入【学生】数据访问层

	/**
	 * @方法说明 【学生】新增并反回主键
	 */
	// @Transactional
	public Long save(Student student) {
		return studentDao.save(student).getId();
	}

	/**
	 * @方法说明 【学生】删除
	 */
	public int delete(Object[] ids) {
		return studentDao.delete(ids);
	}

	/**
	 * @方法说明 【学生】修改
	 */
	public int update(Student student) {
		return studentDao.update(student);
	}

	/**
	 * @方法说明 【学生】分页列表
	 */
	public Page<Student> page(StudentCond cond) {
		return studentDao.page(cond);
	}

	/**
	 * @方法说明 【学生】列表
	 */
	public List<Student> list(StudentCond cond) {
		return studentDao.list(cond);
	}

	/**
	 * @方法说明 【学生】按主键查
	 */
	public Student findById(Long id) {
		return studentDao.findById(id);
	}

	/**
	 * @方法说明 【学生】个数
	 */
	public int count(StudentCond cond) {
		return studentDao.count(cond);
	}
	/**
	 * @方法说明 【学生】批量保存与更新
	 */
	public void saveBatch(List<Student> students) {
		studentDao.saveBatch(students);
	}
}
