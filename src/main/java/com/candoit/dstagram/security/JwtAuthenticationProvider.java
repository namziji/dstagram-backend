package com.candoit.dstagram.security;


import com.candoit.dstagram.security.model.AuthUser;
import com.candoit.dstagram.security.model.SecurityUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthService userAuthService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        AuthUser authUser = (AuthUser) token.getPrincipal();
        String email = token.getName();
        String password = (String) token.getCredentials();
        SecurityUser user = (SecurityUser) userAuthService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(email + "Invalid password");
        }

        if(!user.isEnabled()) {
            throw new BadCredentialsException(email + "invalid user");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
