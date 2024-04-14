package com.conference.payloads;

import java.util.ArrayList;
import java.util.List;

import com.conference.entities.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommitteeDto {
    private int committee_id;
    private String committee_name;
    private List<UserDto> user = new ArrayList<>();
}
