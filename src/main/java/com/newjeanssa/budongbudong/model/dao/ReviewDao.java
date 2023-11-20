package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.review.ReviewAptDto;
import com.newjeanssa.budongbudong.model.dto.review.ReviewUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewDao {
    void insertReview(ReviewAptDto reviewDto);
    List<ReviewAptDto> selectReviewsByAptId(String aptId);
    List<ReviewUserDto> selectReviewsByUserId(Long userId);
    Optional<Long> selectReview(Long reviewId);
    void updateReview(ReviewAptDto reviewDto);
    void deleteReview(Long reviewId);
}
