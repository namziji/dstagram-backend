package com.candoit.dstagram.security;

import com.candoit.dstagram.security.model.SecurityUser;
import com.candoit.dstagram.security.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = tokenUtils.generateJwtToken(securityUser.getAuthUser());
        response.addHeader("Authorization", "Bearer " + token);
    }
}
