package com.example.demo.repo;

import java.util.Map;

import com.example.demo.repo.model.UserRC;

public interface UserRepositoryRC {

	public void save(UserRC user);

	public Map<String, UserRC> findAll();

	public void update(UserRC user);

	public void delete(String id);

	UserRC findByToken(String token);
}
