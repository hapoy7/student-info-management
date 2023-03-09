package com.ldr.manage.base.exam;

import com.ldr.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @类说明 【考试安排】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class ExamService {

	@Autowired
	private ExamDao examDao; // 注入【考试安排】数据访问层

	/**
	 * @方法说明 【考试安排】新增并反回主键
	 */
	// @Transactional
	public Long save(Exam exam) {
		return examDao.save(exam).getId();
	}

	/**
	 * @方法说明 【考试安排】删除
	 */
	public int delete(Object[] ids) {
		return examDao.delete(ids);
	}

	/**
	 * @方法说明 【考试安排】修改
	 */
	public int update(Exam exam) {
		return examDao.update(exam);
	}

	/**
	 * @方法说明 【考试安排】分页列表
	 */
	public Page<Exam> page(ExamCond cond) {
		return examDao.page(cond);
	}

	/**
	 * @方法说明 【考试安排】列表
	 */
	public List<Exam> list(ExamCond cond) {
		return examDao.list(cond);
	}

	/**
	 * @方法说明 【考试安排】按主键查
	 */
	public Exam findById(Long id) {
		return examDao.findById(id);
	}

	/**
	 * @方法说明 【考试安排】个数
	 */
	public int count(ExamCond cond) {
		return examDao.count(cond);
	}
	/**
	 * @方法说明 【考试安排】批量保存与更新
	 */
	public void saveBatch(List<Exam> exams) {
		examDao.saveBatch(exams);
	}
}
