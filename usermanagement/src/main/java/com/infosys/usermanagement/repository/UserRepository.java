package com.infosys.usermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	@Query(value = "{'user.name': ?0}", fields = "{'employees' : 0}")
	public User findByUsername(String userName);

}
