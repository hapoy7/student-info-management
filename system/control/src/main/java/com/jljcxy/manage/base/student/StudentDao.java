package com.jljcxy.manage.base.student;

import com.jljcxy.common.base.BaseDao;
import com.jljcxy.common.base.Page;
import org.springframework.stereotype.Repository;
/**
 * @类说明 【学生】数据访问层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Repository
public class StudentDao extends BaseDao<Student> {
//	StudentDao() { checkTableRef = true; /*启用删除时外键检查*/ }
    /**
     * @方法说明 【学生】分页列表
     */
    public Page<Student> page(StudentCond cond) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.id,t.user_id,t.student_number,t.clazz_id,bc.name clazz_name,t.name,t.sex,t.age,t.phone,t.email,t.photo_url");
        sql.append(" FROM base_student t");
        sql.append("  JOIN base_clazz bc ON bc.id = t.clazz_id");
        sql.append(cond.where());
        return page(sql.toString(), cond, Student.class);
    }
}
