package com.conference.services;

import java.io.IOException;
import java.util.List;

import com.conference.payloads.emailRequestDto;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String text) throws MessagingException;

    public void data_fetch(Integer topic_id, emailRequestDto emailRequestDto) throws MessagingException, IOException;
}
