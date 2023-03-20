package com.jljcxy.manage.base.examrecord;

import com.jljcxy.common.base.BaseDao;
import com.jljcxy.common.base.Page;
import org.springframework.stereotype.Repository;
/**
 * @类说明 【考试记录表】数据访问层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Repository
public class ExamRecordDao extends BaseDao<ExamRecord> {
//	ExamRecordDao() { checkTableRef = true; /*启用删除时外键检查*/ }

    /**
     * @方法说明 【考试记录表】分页列表
     */
    public Page<ExamRecord> page(ExamRecordCond cond) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.id,t.exam_id,t.student_id,bs.name student_name,t.exem_date,t.exem_time_cost,t.exem_score,t.exem_result_level");
        sql.append(" FROM question_exam_record t");
        sql.append(" JOIN base_student bs ON t.student_id = bs.id ");
        sql.append(cond.where());
        return page(sql.toString(), cond, ExamRecord.class);
    }
}
