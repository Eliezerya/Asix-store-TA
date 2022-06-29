package com.Platinum.Asixstore.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class CostumizeFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public CostumizeFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username, password;

        try {
            Map<String, String> mapRequest = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            username = mapRequest.get("email");
            password = mapRequest.get("password");
        } catch (IOException io) {
            throw new AuthenticationServiceException(io.getMessage());
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC512("Binar".getBytes());
        String accessToken = JWT.create().withSubject(user.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000)).
                withIssuer(request.getRequestURL().toString()).
                withClaim("roles",user.getAuthorities().stream().
                        map(GrantedAuthority :: getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String resreshToken = JWT.create().withSubject(user.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000)).
                withIssuer(request.getRequestURL().toString()).sign(algorithm);
        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", resreshToken);
        Map <String, String> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("refresh_token", resreshToken);
        map.put("email", user.getUsername());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), map);
    }
}
