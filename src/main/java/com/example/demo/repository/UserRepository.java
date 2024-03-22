package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

/*
 * Spring boot is containing jpaRepository,which can be used for database 
 * operations. 
 * CrudRepository interface contains the method for basic Crud operations
 * JpaRepsotiory is extending from CrudRepository which contains additonal 
 * features like pagination and sorting
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
