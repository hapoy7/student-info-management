package com.jljcxy.manage.base.major;

import com.jljcxy.common.base.BaseDao;
import com.jljcxy.common.base.Page;
import org.springframework.stereotype.Repository;
/**
 * @类说明 【专业】数据访问层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Repository
public class MajorDao extends BaseDao<Major> {
//	MajorDao() { checkTableRef = true; /*启用删除时外键检查*/ }

    /**
     * @方法说明 【专业】分页列表
     */
    public Page<Major> page(MajorCond cond) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.id,t.name,t.major_number,t.institute_id,bm.name institute_name ");
        sql.append(" FROM base_major t");
        sql.append("  JOIN base_institute bm ON t.institute_id =  bm.id ");
        sql.append(cond.where());
        return page(sql.toString(), cond, Major.class);
    }

}
