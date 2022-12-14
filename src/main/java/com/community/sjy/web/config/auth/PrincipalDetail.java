package com.community.sjy.web.config.auth;

import com.community.sjy.web.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료되면
// UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Data

public class PrincipalDetail implements UserDetails, OAuth2User {
    private User user;
    private Map<String, Object> attributes;


    //일반 로그인
    public PrincipalDetail(User user){
        this.user = user;
    }

    // OAuth 로그인
    public PrincipalDetail(User user, Map<String, Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴 (true 만료x)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨 있는지 (true 잠기지x)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 (true 만료x)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    // 계정 활성화 (true 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }


    //계정이 갖고있는 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return "ROLE_"+user.getRole();}); // 람다식
        return collectors;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
