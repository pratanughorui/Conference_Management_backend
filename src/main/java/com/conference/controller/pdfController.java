package com.conference.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pdfController {
    @Value("${project.folder}")
    private String PDF_FOLDER;

    @GetMapping("/pdf/{authorworkid}")
    public ResponseEntity<byte[]> getPdf(@PathVariable Integer authorworkid) throws IOException {
        System.out.println("asdfffffffff");
        String filename = authorworkid + ".pdf"; // Assuming filename is based on authorworkid

        // Construct the file path
        Path path = Paths.get(PDF_FOLDER, filename);

        // Load PDF file as byte array
        byte[] pdfBytes = Files.readAllBytes(path);

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        // Return response entity
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
