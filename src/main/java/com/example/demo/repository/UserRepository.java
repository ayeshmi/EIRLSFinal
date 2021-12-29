package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReservationDetails;
import com.example.demo.model.User;





@Repository

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<ReservationDetails>{
	Optional<User> findByUsername(String username);
	User findByEmail(String email);
	User findUserByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value="select * from users b where b.new_updates like 'yes'", nativeQuery = true)
	List<User> getUsers();
}
