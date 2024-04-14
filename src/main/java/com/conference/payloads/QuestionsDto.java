package com.conference.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuestionsDto {
    private String qus;

    private String ans;
}
