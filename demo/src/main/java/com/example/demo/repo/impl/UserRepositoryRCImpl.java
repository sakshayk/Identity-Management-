package com.example.demo.repo.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.repo.UserRepositoryRC;
import com.example.demo.repo.model.UserRC;

@Repository
public class UserRepositoryRCImpl implements UserRepositoryRC {

	private RedisTemplate<String, UserRC> redisTemplate;

	private HashOperations<String, String, UserRC> hashOperations;

	public UserRepositoryRCImpl(RedisTemplate<String, UserRC> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(UserRC user) {
		hashOperations.put("USER", user.getToken(), user);
		redisTemplate.expire("USER", 120, TimeUnit.SECONDS);
	}

	@Override
	public Map<String, UserRC> findAll() {
		return hashOperations.entries("USER");
	}

	@Override
	public UserRC findByToken(String token) {
		return hashOperations.get("USER", token);
	}

	@Override
	public void update(UserRC user) {
		save(user);
	}

	@Override
	public void delete(String token) {
		hashOperations.delete("USER", token);
	}
}
