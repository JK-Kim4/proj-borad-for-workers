package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    SUPER_ADMIN("ROLE_SUPER_ADMIN,ROLE_ADMIN,ROLE_USER,ROLE_GUEST"),
    ADMIN("ROLE_ADMIN,ROLE_USER,ROLE_GUEST"),
    USER("ROLE_USER,ROLE_GUEST"),
    GUEST("ROLE_GUEST");

    private final String value;

    public String getHighestRoleValue(){
        return this.getValue().split(",")[0];
    }

}
