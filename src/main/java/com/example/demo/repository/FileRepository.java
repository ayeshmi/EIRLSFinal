package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FileResponse;

public interface FileRepository extends JpaRepository<FileResponse, Long>{
	
}