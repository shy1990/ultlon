package com.sj1688.ultlon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj1688.ultlon.dao.mysql.UserRepository;
import com.sj1688.ultlon.domain.User;
import com.sj1688.ultlon.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

}
