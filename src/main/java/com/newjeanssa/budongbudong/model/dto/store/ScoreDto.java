package com.newjeanssa.budongbudong.model.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    String aptId;
    double totalScore;
    double facilitiesScore;
    double educationScore;
    double storeScore;
    double medicalScore;
    double foodScore;
}
