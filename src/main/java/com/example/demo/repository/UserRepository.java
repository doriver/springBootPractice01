package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUsername(String username);
	List<User> findByEmail(String email);
	
	List<User> findByUsernameAndEmail(String username, String email);
	
	@Query("SELECT username FROM User WHERE id = :id")
	String findUsernameById(@Param("id") Long id);

	@Query("SELECT username, email FROM User WHERE email = :email")
	List<Object> findTwoFieldsByEmail(@Param("email") String email);
	
	List<User> findAllByOrderByEmailDesc();
}
