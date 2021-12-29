package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Video;
@Repository
public interface VideoRepository extends JpaRepository<Video,Long>{
	Video findByTitle(String title);
	//ArrayList<Video> video = new ArrayList<>(2);
	
	List<Video> findByCategory(String category);
	
	Boolean existsByTitle(String title);
	
	@Query(value="select * from video v where v.title like %:keyword% or v.description like %:keyword% or v.category like %:keyword% ", nativeQuery = true)
	List<Video> search(@Param("keyword")String a);
	
	@Query(value="select * from video where age_limitation=:keyword2 AND category=:keyword1 ", nativeQuery = true)
	List<Video> getVideoAgeLimitation(@Param("keyword1")String b,@Param("keyword2")String a);
}
