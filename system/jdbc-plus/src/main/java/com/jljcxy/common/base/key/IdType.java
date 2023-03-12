package com.jljcxy.common.base.key;

/**
 * @summary 【主键类型】常量
 * @author 高振中
 * @date 2022-05-22 10:00:00
 **/
public interface IdType {
	String UUID = "uuid";/* UUID主键 */
	String AUTO = "auto";/* 数据库自增主键 */
	String SNOW = "snow";/* 雪花算法分布式主键 */
}
