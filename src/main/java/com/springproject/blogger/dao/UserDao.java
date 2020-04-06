package com.springproject.blogger.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springproject.blogger.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username") String username);

	@Query("select u from User u where u.username = :username")
	Optional<User> findByUsernameOptional(@Param("username") String username);

}
