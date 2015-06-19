package com.sj1688.ultlon.dao.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj1688.ultlon.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
