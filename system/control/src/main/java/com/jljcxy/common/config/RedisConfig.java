package com.jljcxy.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @summary <a href="https://www.jianshu.com/u/3bd57d5f1074">...</a>
 * @author 高振中
 * @date 2022-05-30 14:22:24
 **/
@Configuration
public class RedisConfig {
	@Bean
	public <K, V> RedisTemplate<K, V> template(@Autowired RedisConnectionFactory factory) {
		RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}
}
