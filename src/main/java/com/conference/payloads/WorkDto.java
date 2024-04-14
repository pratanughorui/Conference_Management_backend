package com.conference.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WorkDto {
    @NotEmpty
    private String track;
    @NotEmpty
    private String key_words;
    @NotEmpty
    private String abstractText;
    // @NotEmpty
    // private MultipartFile pdfFile;
    @NotEmpty
    private String pdf_name;
}
