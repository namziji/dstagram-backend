package com.candoit.dstagram.security;

import com.candoit.dstagram.security.utils.TokenUtils;
import com.candoit.dstagram.user.UserRepository;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenUtils tokenUtils;
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, TokenUtils tokenUtils, UserRepository userRepository) {
        super(authenticationManager);
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(authHeader);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(String authHeader) {
        String token = authHeader.replace("Bearer", "").trim();
        try {
            String email = tokenUtils.getEmailFromToken(token);
            if (email == null) {
                return null;
            }

            return userRepository.findByEmail(email)
                    .map(user -> new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority(user.getRole()))))
                    .orElse(null);
        } catch (MalformedJwtException e) {
            log.error("Failed to authenticated: " + token, e);
            return null;

        }
    }
}
