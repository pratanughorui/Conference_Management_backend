package com.conference.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.conference.payloads.AuthorDto;
import com.conference.payloads.AuthorWorkDto;
import com.conference.payloads.CoAuthorsDto;

public interface AuthorService {
    AuthorWorkDto CreateAuthorWork(AuthorWorkDto authorWorkDto, Integer topic_id, Integer conference_id,
            List<CoAuthorsDto> coauthors);

    void UploadFile(String path, String filename, MultipartFile file) throws IOException;

    List<AuthorWorkDto> getallauthors(Integer conference_id);

    AuthorWorkDto getauthorworkbyid(Integer authorwork_id);
    // AuthorDto CreateNewAuthor(AuthorDto authorDto);

    // Set<AuthorDto> allworkByconference(Integer conference_id);

}
