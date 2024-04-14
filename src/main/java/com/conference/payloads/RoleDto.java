package com.conference.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private int role_id;
    @NotEmpty
    private String role_name;
}
