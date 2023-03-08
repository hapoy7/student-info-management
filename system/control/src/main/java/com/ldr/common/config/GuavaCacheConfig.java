package com.ldr.common.config;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ldr.manage.sys.common.LoginVO;

/**
 * @summary【Guava缓存】配置类(根据实际情况可替换成redis)
 * @author 高振中
 * @date 2021-02-01 20:20:20
 **/
@Configuration
public class GuavaCacheConfig {

	/**
	 * 【用户登录信息缓存】
	 **/
	@Bean
	public Cache<Long, LoginVO> cacheStations() {
		return CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.HOURS).build();
	}

}
