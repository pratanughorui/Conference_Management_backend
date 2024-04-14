package com.conference.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GradingDto {

    private String max_grade;
    private String min_grade;
    private int score;
}
