package com.conference.controller;

import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class coockie {
    @GetMapping("/test")
    public String setCookie() {
        return "test";
    }

}
