package com.fiberhome.authservice.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.time.Duration;
import java.util.Optional;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.pass}")
    private String redisPass;

    @Bean
    @Primary
    JedisConnectionFactory jedisConnectionFactory() throws Exception {
        RedisStandaloneConfiguration factory = new RedisStandaloneConfiguration();
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        if (redisPass != null) {
            factory.setPassword(redisPass);
        }
        JedisClientConfiguration jedisClientConfiguration =
                JedisClientConfiguration.builder().usePooling().build();

        return new JedisConnectionFactory(factory,jedisClientConfiguration);
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() throws Exception {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());

        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }
}
