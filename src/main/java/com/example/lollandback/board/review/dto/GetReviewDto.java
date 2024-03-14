package com.example.lollandback.board.review.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetReviewDto {
    private List<ReviewDto> reviewList;
    private Long totalReviews;
    private boolean canLeaveReview;

    public GetReviewDto () {
    }

    public GetReviewDto(List<ReviewDto> reviewList, Long totalReviews, boolean canLeaveReview) {
        this.reviewList = reviewList;
        this.totalReviews = totalReviews;
        this.canLeaveReview = canLeaveReview;
    }
}
