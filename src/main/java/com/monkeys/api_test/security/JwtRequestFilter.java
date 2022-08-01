package com.monkeys.api_test.security;

import com.monkeys.api_test.services.implementations.LoginServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginServiceImpl loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = httpServletRequest.getHeader("authorization");
            if (token != null) {
                token = token.substring(7);
                Claims claims = jwtTokenUtil.validateToken(token);
                UserDetails userDetails = loginService.loadUserByUsername(claims.getSubject());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = jwtTokenUtil.getAuthentication(token, userDetails);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            (httpServletResponse).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}