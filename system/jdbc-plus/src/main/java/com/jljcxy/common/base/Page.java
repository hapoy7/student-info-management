package com.jljcxy.common.base;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * @summary【分页】数据
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */
@Setter
@Getter
@AllArgsConstructor
public class Page<T> {
	private List<T> dataList; /* 数据列表 */
 	private int size; /* 每页记录数(页大小) */
	private int rowCount;/* 记录总数 */
	private int page;/* 当前页 */
}
