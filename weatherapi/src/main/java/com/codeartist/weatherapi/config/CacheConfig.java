package com.codeartist.weatherapi.config;

import com.codeartist.weatherapi.dto.ResponseDto;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.*;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableRedisRepositories
public class CacheConfig {
    ResponseDto responseDto= new ResponseDto();
@Bean
public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
    RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson3JsonRedisSerializer(new ObjectMapper()));
    return RedisCacheManager.builder(redisConnectionFactory).build();
}
}
