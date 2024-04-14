package com.conference.services.imple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.conference.entities.Authors_work;
import com.conference.entities.CoAuthors;
import com.conference.entities.Reviewer;
import com.conference.entities.Topics;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.emailRequestDto;
import com.conference.repositories.TopicRepo;
import com.conference.services.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImple implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TopicRepo topicRepo;

    // @Autowired
    // private JavaMailSender mailSender;

    private String msg = "Thank you for your willingness to serve as a reviewer. Peer review is one of the most important activities of our Society, and your help is appreciated. Written comments are usually the most helpful part of a review. Please provide comments on the second page or on separate sheets. The grading section below is intended to help identify key points for written comments, and also to allow comparisons among different reviewers. A good paper should have a high overall score, but does not have to score well in all aspects to be acceptable. For example, a concise, critical review paper is a valuable publication, although it might have little intrinsic originality. A paper that introduces important new concepts might be valuable even with limited experimental work.";

    @Override
    public void sendEmail(String to, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("ghoruipratanu@gmail.com");
        helper.setTo(to);
        helper.setSubject("Request to review the following paper");
        helper.setText(htmlContent, true); // Set the second parameter to true to indicate HTML content
        mailSender.send(message);
    }

    @Override
    public void data_fetch(Integer topic_id, emailRequestDto emailRequestDto) throws MessagingException, IOException {
        Topics topic = this.topicRepo.findById(topic_id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id", topic_id));
        List<Authors_work> authors_works = topic.getAuthors();

        for (Authors_work aw : authors_works) {
            String title = aw.getTitle();
            int paper_id = aw.getAuthor_id();
            String name = aw.getName();
            String abs = aw.getAbstractText();
            String coauth = "";
            for (CoAuthors co : aw.getCoAuthors()) {
                coauth += co.getName();
                coauth += ',';
            }
            String text = "<h2>Title:</h2> " + title +
                    "<h2>Paper ID:</h2> " + paper_id +
                    "<h2>Name:</h2> " + name +
                    "<h2>Co-authors:</h2> " + coauth +
                    "<h2>Last date of review:</h2>" + emailRequestDto.getDate() +
                    "<h2>Message:</h2>" + msg +
                    "<h2>Abstract:</h2> " + abs +
                    "<h2>Designation:</h2>" + emailRequestDto.getDesignation();

            for (Reviewer re : aw.getReviewers()) {
                String t = text;

                String acceptUrl = "http://127.0.0.1:3000/review-paper2?reviewerId=" + re.getReviewer_id() +
                        "&authorWorkId=" + aw.getAuthor_id();
                t += "<br><br>To review the paper, please confirm your acceptance by clicking here: " +
                        "<a href=\"" + acceptUrl + "\">Accept</a>" +
                        "<br>Upon acceptance, you will be directed to the review page." +
                        "<br><br>If you wish to reject the review, please click here: " +
                        "<a href=\"" + "rejectUrl" + "\">Reject</a>" +
                        "<br>This will reject the review request.";
                ;
                this.sendEmail(re.getEmail(), t);
            }
        }
    }

}
