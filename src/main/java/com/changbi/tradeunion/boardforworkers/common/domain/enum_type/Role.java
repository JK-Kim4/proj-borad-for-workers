package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    SUPER_ADMIN("ROLE_SUPER_ADMIN,ROLE_ADMIN,ROLE_USER"),
    ADMIN("ROLE_ADMIN,ROLE_USER"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private final String value;

}
