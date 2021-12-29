package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VideoReservation;
@Repository
public interface VideoReservationRepository extends JpaRepository<VideoReservation,Long> , JpaSpecificationExecutor<VideoReservation>{

VideoReservation findByEmail(String email);
	
	
	
	@Query(value="select * from video_reservation b where b.email like :keyword and status= 'ongoing'", nativeQuery = true)
	List<VideoReservation> getAllOngoingVideoReservation(@Param("keyword")String a);
	
	@Query(value="select * from book_reservation b where status != 'completed'", nativeQuery = true)
	List<VideoReservation> viewAllBookReservation();
	
	@Query(value="select COUNT(*) from video_reservation b where b.video_id like :keyword", nativeQuery = true)
	int countOfVideosById(@Param("keyword")Long a);
	
	@Query(value="select * from book_reservation b where b.book_id like :keyword ", nativeQuery = true)
	List<VideoReservation> getBookReservationByBookId(@Param("keyword")String a);
	
	@Query(value="SELECT  DISTINCT(user_id)  FROM book_reservation where overdue_payment != 0; ", nativeQuery = true)
	List<Long> getBlackListCutomers();

	@Query(value="select * from video_reservation b where b.video_id like :keyword ", nativeQuery = true)
	List<VideoReservation> getVideoReservationByVideoId(@Param("keyword")Long id);
	
	@Query(value="select * from video_reservation b where b.email like :keyword and lending_status='cart' and status is null", nativeQuery = true)
	List<VideoReservation> getAllCartVideoReservation(@Param("keyword")String email);
	
	
	
}
