package com.jljcxy.manage.sys.fieldconf;

import com.jljcxy.common.base.Page;
import com.jljcxy.common.base.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @类说明 【字段配置】业务逻辑层
 * @author 高振中
 * @date 2023-02-02 15:51:45
 **/
@Service
public class FieldConfService {

	@Autowired
	private FieldConfDao fieldConfDao; // 注入【字段配置】数据访问层

	/**
	 * @方法说明 【字段配置】新增并反回主键
	 */
	public Long save(FieldConfVo vo) {
		String cond = Arrays.asList(vo.getConds()).stream().map(i -> i).collect(Collectors.joining(Const.SQL.COMMA));
		String grid = Arrays.asList(vo.getGrids()).stream().map(i -> i).collect(Collectors.joining(Const.SQL.COMMA));
		FieldConf fieldConf = FieldConf.builder().userId(vo.getUserId()).cond(cond).grid(grid).component(vo.getComponent()).build();
		return fieldConfDao.save(fieldConf).getId();
	}

	/**
	 * @方法说明 【字段配置】修改
	 */
	public int update(FieldConf fieldConf) {
		return fieldConfDao.update(fieldConf);
	}

	/**
	 * @方法说明 【字段配置】分页列表
	 */
	public Page<FieldConf> page(FieldConfCond cond) {
		return fieldConfDao.page(cond);
	}

	/**
	 * @方法说明 【字段配置】列表
	 */
	public FieldConfVo list(FieldConfCond cond) {
		List<FieldConf> confs = fieldConfDao.list(cond);
		if (confs.size() == 0)
			return new FieldConfVo();
		FieldConf conf = confs.get(0);
		return new FieldConfVo(conf.getCond().split(","), conf.getGrid().split(","));
	}
}
