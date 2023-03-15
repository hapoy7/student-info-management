package com.jljcxy.manage.base.clazz;

import com.jljcxy.common.base.Page;
import org.springframework.stereotype.Repository;
import com.jljcxy.common.base.BaseDao;
/**
 * @类说明 【班级】数据访问层
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@Repository
public class ClazzDao extends BaseDao<Clazz> {
//	ClazzDao() { checkTableRef = true; /*启用删除时外键检查*/ }
    /**
     * @方法说明 【班级】分页列表
     */
    public Page<Clazz> page(ClazzCond cond) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.id,t.name,bm.name major_name,t.major_id,t.institute_id, bi.name institute_name");
        sql.append(" FROM base_clazz t");
        sql.append(" JOIN base_major bm ON t.major_id = bm.id JOIN base_institute bi ON t.institute_id = bi.id ");
        sql.append(cond.where());
        return page(sql.toString(), cond, Clazz.class);
    }
}
