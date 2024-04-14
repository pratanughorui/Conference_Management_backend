package com.conference.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Email {
    private String subject;
    private String message;
    private List<String> to;

    public Email(String subject, String message, List<String> to) {
        this.subject = subject;
        this.message = message;
        this.to = to;
    }
}
