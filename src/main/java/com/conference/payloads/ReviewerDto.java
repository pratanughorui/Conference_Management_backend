package com.conference.payloads;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewerDto {
    private int reviewer_id;
    private String name;
    private String address;
    private String place;
    private String state;
    private String affliation;
    private String country;
    private String password;
    private String mobile;
    private String email;
}
