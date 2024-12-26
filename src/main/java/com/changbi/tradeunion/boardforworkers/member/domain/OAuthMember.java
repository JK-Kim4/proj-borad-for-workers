package com.changbi.tradeunion.boardforworkers.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OAuthMember {

    @Id
    private String id;

    private String provider;

    private String email;

    private String accessToken;

    private String refreshToken;

    private String name;
}
