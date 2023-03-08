package com.jljcxy.manage.base.examrecord;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldr.common.base.Page;
/**
 * @类说明 【考试记录表】业务逻辑层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Service
public class ExamRecordService {

	@Autowired
	private ExamRecordDao examRecordDao; // 注入【考试记录表】数据访问层

	/**
	 * @方法说明 【考试记录表】新增并反回主键
	 */
	// @Transactional
	public Long save(ExamRecord examRecord) {
		return examRecordDao.save(examRecord).getId();
	}

	/**
	 * @方法说明 【考试记录表】删除
	 */
	public int delete(Object[] ids) {
		return examRecordDao.delete(ids);
	}

	/**
	 * @方法说明 【考试记录表】修改
	 */
	public int update(ExamRecord examRecord) {
		return examRecordDao.update(examRecord);
	}

	/**
	 * @方法说明 【考试记录表】分页列表
	 */
	public Page<ExamRecord> page(ExamRecordCond cond) {
		return examRecordDao.page(cond);
	}

	/**
	 * @方法说明 【考试记录表】列表
	 */
	public List<ExamRecord> list(ExamRecordCond cond) {
		return examRecordDao.list(cond);
	}

	/**
	 * @方法说明 【考试记录表】按主键查
	 */
	public ExamRecord findById(Long id) {
		return examRecordDao.findById(id);
	}

	/**
	 * @方法说明 【考试记录表】个数
	 */
	public int count(ExamRecordCond cond) {
		return examRecordDao.count(cond);
	}
	/**
	 * @方法说明 【考试记录表】批量保存与更新
	 */
	public void saveBatch(List<ExamRecord> examRecords) {
		examRecordDao.saveBatch(examRecords);
	}	
}