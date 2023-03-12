package com.jljcxy.common.base.key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *【主键生成器】
 * @author 高振中
 * @date 2022-05-22 10:00:00
 **/
@Component
public class IdGenerator {
	@Value("${spring.workerId}")
	private long workerId = 0;/* 服务ID,集群部署时每个服务配成不同值 */
	@Value("${spring.dataCenterId}")
	private long dataCenterId = 0;/* 数据中心ID */
	private final SnowflakeId idWorker = new SnowflakeId(workerId, dataCenterId);/* 雪花主键算法 */

	/**
	 * 生成主键
	 */
	public long create() {
		return idWorker.nextId();
	}
}
