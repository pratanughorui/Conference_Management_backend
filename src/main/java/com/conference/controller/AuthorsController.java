package com.conference.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.conference.payloads.ApiResponse;
import com.conference.payloads.AuthorDto;
import com.conference.payloads.AuthorWorkDto;
import com.conference.payloads.CoAuthorsDto;
import com.conference.payloads.GradingDto;
import com.conference.payloads.QuestionsDto;
import com.conference.payloads.ReviewDto;
import com.conference.services.AuthorService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    private AuthorService authorService;
    @Value("${project.folder}")
    private String path;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/uploadwork/{topic_id}/{conference_id}")
    public ResponseEntity<?> UploadWork(@RequestParam("pdfFiles") MultipartFile files,
            @RequestParam("name") String authorWorkDtoJson, @RequestParam("coauthor") String co,
            @PathVariable Integer topic_id,
            @PathVariable Integer conference_id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // System.out.println("ddd");
        // System.out.println(authorWorkDtoJson);
        // System.out.println(co);
        AuthorWorkDto authorWorkDto = objectMapper.readValue(authorWorkDtoJson,
                AuthorWorkDto.class);
        List<CoAuthorsDto> coAuthors = objectMapper.readValue(co, new TypeReference<List<CoAuthorsDto>>() {
        });
        // AuthorWorkDto authorWorkDto =
        // objectMapper.readValue(authorWorks,AuthorWorkDto.class);
        // System.out.println("ddd");
        // for (CoAuthorsDto coAuthor : coAuthors) {
        // System.out.println(coAuthor.toString()); // Example usage
        // }
        // System.out.println(authorWorkDto.toString());
        String filename = files.getOriginalFilename();
        authorWorkDto.setPdf_name(filename);
        System.out.println(filename);
        AuthorWorkDto at = this.authorService.CreateAuthorWork(authorWorkDto,
                topic_id, conference_id, coAuthors);
        if (at == null) {
            return new ResponseEntity<>(Map.of("message", "Conference not available"),
                    HttpStatus.OK);
        }
        filename = at.getPdf_name();
        this.authorService.UploadFile(path, filename, files);
        return new ResponseEntity<>(authorWorkDto, HttpStatus.OK);

    }

    // @PostMapping("/createnewauthor")
    // public ResponseEntity<AuthorDto> createnewauthor(@RequestBody AuthorDto
    // authorDto) {
    // AuthorDto newauthorDto = this.authorService.CreateNewAuthor(authorDto);
    // return new ResponseEntity<AuthorDto>(newauthorDto, HttpStatus.OK);
    // }

    // @GetMapping("/getallauthorwork/{conference_id}")
    // public Set<AuthorDto> getallauthorwork(@PathVariable Integer conference_id) {
    // Set<AuthorDto> work_list =
    // this.authorService.allworkByconference(conference_id);
    // return work_list;
    // }

    @GetMapping("/getallauthors/{conference_id}")
    public List<AuthorWorkDto> getAllAuthors(@PathVariable Integer conference_id) {
        List<AuthorWorkDto> savedauthors = this.authorService.getallauthors(conference_id);
        return savedauthors;
    }

    @GetMapping("/getauthorwork/{authorwork_id}")
    public ResponseEntity<AuthorWorkDto> getAuthorsworkbyid(@PathVariable Integer authorwork_id) {
        AuthorWorkDto savedauthors = this.authorService.getauthorworkbyid(authorwork_id);
        return ResponseEntity.ok(savedauthors);
    }

    // @GetMapping("/getConferenceFromSession")
    // public ResponseEntity<?> getConferenceFromSession(HttpSession session) {

    // System.out.println("ababa" + session.getAttribute("conference_id"));
    // // this.conferenceService.getdatafromsession();
    // return new ResponseEntity<>("done", HttpStatus.OK);

    // }

}
