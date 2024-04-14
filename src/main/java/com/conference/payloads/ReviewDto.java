package com.conference.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewDto {
    private int reviewerId;
    private int paperId;
    private String reviewDate;
    private String acceptance;
    private int totalScore;
}
