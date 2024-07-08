package com.changbi.tradeunion.boardforworkers.member.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginInformation {

    @NotNull
    private String memberEmail;

    @NotNull
    private String memberPassword;
}
