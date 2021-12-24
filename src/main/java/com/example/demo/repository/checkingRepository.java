package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Checking;

@Repository
public interface checkingRepository extends JpaRepository<Checking,Long> {

}
