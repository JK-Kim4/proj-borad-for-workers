package com.changbi.tradeunion.boardforworkers.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
public class MemberDetail implements UserDetails {

    private Member member;

    public MemberDetail(Member member){
        this.member = member;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        for(String s : member.getRole().getValue().split(",")){
            collection.add(new SimpleGrantedAuthority(s));
        }

        return collection;
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberEmail();
    }
}
