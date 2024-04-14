package com.conference.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.conference.entities.Users;
import com.conference.payloads.AuthorWorkDto;
import com.conference.payloads.ConferenceDto;
import com.conference.payloads.UserDto;
import com.conference.services.ConferenceService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conference")
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;

    @PostMapping("/createConference")
    public ResponseEntity<ConferenceDto> createConference(@RequestBody ConferenceDto conferenceDto) {
        try {
            ConferenceDto createConferenceDto = this.conferenceService.createConference(conferenceDto);
            return new ResponseEntity<ConferenceDto>(createConferenceDto, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("conference in already created");
        }
    }

    // @PostMapping("/setSessionData/{conference_id}")
    // public ResponseEntity<?> setSessionData(@PathVariable Integer conference_id,
    // HttpServletRequest request) {
    // HttpSession session = request.getSession();
    // System.out.println(conference_id);
    // session.setAttribute("conference_id", conference_id);
    // System.out.println(session.getAttribute("conference_id"));
    // return new ResponseEntity<>("done", HttpStatus.OK);
    // }

    // @GetMapping("/getConferenceFromSession")
    // public ResponseEntity<ConferenceDto>
    // getConferenceFromSession(HttpServletRequest request) {

    // HttpSession session = request.getSession();
    // Integer ci = (Integer) session.getAttribute("conference_id");
    // System.out.println("conid :" + ci);
    // return ResponseEntity.ok(this.conferenceService.getConferenceById(ci));
    // // return ResponseEntity.ok(ci);
    // }
    @GetMapping("/setSessionData/{conference_id}")
    public ResponseEntity<?> setSessionData(@PathVariable Integer conference_id, HttpSession session) {

        session.setAttribute("con", conference_id);
        System.out.println(conference_id);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

    // Cookie cookie = new Cookie("conference_id", String.valueOf(conference_id));
    // cookie.setMaxAge(60 * 60 * 24);
    // cookie.setSecure(true); // Ensure cookie is sent only over

    // cookie.setHttpOnly(true); // Recommended for security
    // // cookie.setPath("/"); // Set the cookie's path as needed
    // cookie.setMaxAge(24 * 60 * 60); // Set cookie expiration time in seconds
    // response.addCookie(cookie);

    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        // Create a cookie
        System.out.println("dfsdfsfd");
        Cookie cookie = new Cookie("conference_id", "1");

        // Add cookie to the response
        response.addCookie(cookie);

        return "Cookie has been set!";
    }

    @GetMapping("/getConferenceFromSession")
    public String getConferenceFromSession(HttpSession session) {
        return "Conference ID: " + session.getAttribute("con");
    }

    @GetMapping("/deleteSession")
    public ResponseEntity<?> deleteSession(HttpServletRequest request) {
        request.getSession().invalidate();
        // System.out.println("this is session data " +
        // session.getAttribute("conference_id"));
        // this.conferenceService.getdatafromsession();
        return new ResponseEntity<>("done", HttpStatus.OK);

    }

    // @PutMapping("/updateConference/{conference_id}")
    // public ResponseEntity<ConferenceDto> updateConference(@Valid @RequestBody
    // ConferenceDto conferenceDto,
    // @PathVariable Integer conference_id) {
    // ConferenceDto updatedConferenceDto =
    // this.conferenceService.updateConference(conferenceDto, conference_id);
    // return new ResponseEntity<>(updatedConferenceDto, HttpStatus.CREATED);
    // }

    @GetMapping("/getAllConferencebtwdate")
    public ConferenceDto getAllConferencebtwdate() {
        System.out.println("ppppfff");
        ConferenceDto Conference = this.conferenceService.getAllConferenceBtwDate();

        return Conference;
    }

    @GetMapping("/getAllConference")
    public List<ConferenceDto> getAllConference() {
        List<ConferenceDto> allConference = this.conferenceService.getallConference();
        return allConference;
    }

    // @DeleteMapping("/deleteConference/{conference_id}")
    // public ResponseEntity<?> deleteConference(@PathVariable Integer
    // conference_id) {
    // this.conferenceService.deleteConference(conference_id);
    // return new ResponseEntity<>(Map.of("message", "Conference Deleted
    // Successfully"), HttpStatus.OK);
    // }

    @GetMapping("/getConference/{conference_id}")
    public ResponseEntity<ConferenceDto> getConference(@PathVariable Integer conference_id) {
        // ConferenceDto
        // conferenceDto=this.conferenceService.getConferenceById(conference_id);
        return ResponseEntity.ok(this.conferenceService.getConferenceById(conference_id));
    }

    // @GetMapping("/getallusersbyconference/{conference_id}")
    // public Set<UserDto> getallusersbyconference(@PathVariable Integer
    // conference_id) {
    // Set<UserDto> ans =
    // this.conferenceService.GetAllUsersByConference(conference_id);
    // return ans;
    // }

    // @GetMapping("/getauth/{conference_id}")
    // public Set<Author_Work> getauth(@PathVariable Integer conference_id) {
    // Set<Author_Work> ans = this.conferenceService.getauth(conference_id);
    // return ans;
    // }

}
