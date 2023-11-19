package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.review.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewDao {
    void insertReview(ReviewDto reviewDto);
    List<ReviewDto> selectReviewsByAptId(String aptId);
    List<ReviewDto> selectReviewsByUserId(Long userId);
    Optional<ReviewDto> selectReview(Long reviewId);
    void updateReview(ReviewDto reviewDto);
    void deleteReview(Long reviewId);
}
