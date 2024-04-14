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
    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("sdfgfs");
        // Create a cookie
        Cookie cookie = new Cookie("user", "JohnDoe");

        // Add cookie to the response
        response.addCookie(cookie);

        return "Cookie has been set!";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(value = "user", defaultValue = "Guest") String username) {
        return "Username: " + username;
    }
}
