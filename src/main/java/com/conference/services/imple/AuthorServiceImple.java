package com.conference.services.imple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.conference.config.AppConstants;

import com.conference.entities.Authors_work;
import com.conference.entities.CoAuthors;
import com.conference.entities.Conference;
import com.conference.entities.Role;
import com.conference.entities.Topics;
import com.conference.entities.Users;

import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.AuthorDto;
import com.conference.payloads.AuthorWorkDto;
import com.conference.payloads.CoAuthorsDto;
import com.conference.payloads.ConferenceDto;
import com.conference.payloads.UserDto;

import com.conference.repositories.Authors_workRepo;
import com.conference.repositories.ConferenceRepo;
import com.conference.repositories.TopicRepo;
import com.conference.repositories.UserRepo;

import com.conference.services.AuthorService;

@Service
public class AuthorServiceImple implements AuthorService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConferenceRepo conferenceRepo;

    @Autowired
    private Authors_workRepo authors_workRepo;

    @Autowired
    private TopicRepo topicRepo;

    @Override
    public AuthorWorkDto CreateAuthorWork(AuthorWorkDto authorWorkDto, Integer topic_id, Integer conference_id,
            List<CoAuthorsDto> coauthors) {
        Topics topic = this.topicRepo.findById(topic_id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", topic_id));
        Conference conference = this.conferenceRepo.findById(conference_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cpmference", "id",
                        conference_id));
        // System.out.println(authorWorkDto.toString());
        // System.out.println(authorWorkDto.getCoAuthors().size());
        Authors_work authors_work = this.modelMapper.map(authorWorkDto, Authors_work.class);
        authors_work.setTopics(topic);
        authors_work.setConferences(conference);
        List<CoAuthors> ca = coauthors.stream()
                .map(con -> this.modelMapper.map(con, CoAuthors.class))
                .collect(Collectors.toList());
        // for (CoAuthors co : ca) {
        // // System.out.println(co.toString());
        // authors_work.getCoAuthors().add(co);
        // }
        for (CoAuthors co : ca) {
            // authors_work.getCoAuthors().add(co);
            co.setAuthors_work(authors_work); // Make sure this line is added
        }
        // authors_work.getCoAuthors().addAll(ca);
        authors_work.setCoAuthors(ca);

        Authors_work saved_authors_work = this.authors_workRepo.save(authors_work);
        String pdfname = authorWorkDto.getPdf_name();
        String filename = Integer.toString(saved_authors_work.getAuthor_id())
                .concat(pdfname.substring(pdfname.lastIndexOf(".")));
        saved_authors_work.setPdf_name(filename);

        // saved_authors_work.setCoAuthors(ca);
        // List<CoAuthorsDto> ca = authorWorkDto.getCoAuthors();
        // System.out.println(ca.get(0));

        authors_work = this.authors_workRepo.save(saved_authors_work);
        return this.modelMapper.map(authors_work, AuthorWorkDto.class);

    }

    // public Author_Work dtoToentity(AuthorWorkDto authorWorkDto) {
    // Author_Work author_Work = new Author_Work();
    // // author_Work.setWork_id(authorWorkDto.getWork_id());
    // author_Work.setAbstractText(authorWorkDto.getAbstractText());

    // author_Work.setTrack(authorWorkDto.getTrack());
    // // author_Work.setPdf_name(authorWorkDto.getPdf_name());
    // author_Work.setKey_words(authorWorkDto.getKey_words());
    // return author_Work;
    // }

    // public AuthorWorkDto entityTodto(Author_Work author_Work) {
    // AuthorWorkDto authorWorkDto = new AuthorWorkDto();
    // // authorWorkDto.setWork_id(author_Work.getWork_id());
    // // authorWorkDto.setPdf_name(author_Work.getPdf_name());
    // authorWorkDto.setAbstractText(author_Work.getAbstractText());
    // authorWorkDto.setKey_words(author_Work.getKey_words());

    // authorWorkDto.setTrack(author_Work.getTrack());

    // // authorWorkDto.setAuthor(this.entityTodto(author_Work.getAuthor()));
    // // authorWorkDto.setConference(this.modelMapper.map(conference,
    // // ConferenceDto.class));
    // return authorWorkDto;
    // }

    @Override
    public void UploadFile(String path, String filename, MultipartFile file) {
        String filepath = path + File.separator + filename;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(filepath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * @Override
     * public AuthorDto CreateNewAuthor(AuthorDto authorDto) {
     * String conference_name = authorDto.getConference_name();
     * Conference conferences =
     * this.conferenceRepo.findByConference_title(conference_name);
     * if (conferences == null) {
     * return null;
     * }
     * 
     * Authors author = this.dtoToentity(authorDto);
     * author.setConference(conferences);
     * Authors createdauthor = this.authorRepo.save(author);
     * return this.entityTodto(createdauthor);
     * // String conference_name = userDto.getConference_name();
     * // Users user = this.modelMapper.map(userDto, Users.class);
     * // user.setPassword(user.getPassword());
     * // Role role = this.roleRepo.findById(AppConstants.author).get();
     * 
     * // Set<Conference> conference =
     * // this.conferenceRepo.findByConference_name(conference_name);
     * // if (conference == null) {
     * // return null;
     * // }
     * // user.getRoles().add(role);
     * // user.setConferences(conference);
     * // Users newuser = this.userRepo.save(user);
     * // return this.modelMapper.map(newuser, UserDto.class);
     * 
     * }
     */

    @Override
    public List<AuthorWorkDto> getallauthors(Integer conference_id) {
        // TODO Auto-generated method stub
        Conference conference = this.conferenceRepo.findById(conference_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", conference_id));
        List<Authors_work> authors_works = conference.getAuthors();
        List<AuthorWorkDto> authorWorkDtos = authors_works.stream()
                .map(con -> this.modelMapper.map(con, AuthorWorkDto.class))
                .collect(Collectors.toList());
        return authorWorkDtos;
    }

    @Override
    public AuthorWorkDto getauthorworkbyid(Integer authorwork_id) {
        Authors_work authorWork = this.authors_workRepo.findById(authorwork_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", authorwork_id));
        return this.modelMapper.map(authorWork, AuthorWorkDto.class);
    }

    // @Override
    // public Set<AuthorDto> allworkByconference(Integer conference_id) {
    // Conference conference = this.conferenceRepo.findById(conference_id)
    // .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
    // conference_id));
    // Set<AuthorDto> authorDtos = new HashSet<>();
    // for (Authors author : conference.getAuthor()) {
    // AuthorDto authorDto = this.entityTodto(author);
    // authorDto.setAuthorWorkDto(this.entityTodto(author.getAuthorWork()));
    // authorDtos.add(authorDto);
    // }
    // return authorDtos;
    // }

}
