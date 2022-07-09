package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BuyerDto;
import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.Role;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.UserService;
import com.Platinum.Asixstore.Service.impl.UserLoginServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Transactional
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserLoginServiceImpl userLoginService;
    @Autowired
    UserRepo userRepo;




    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    @GetMapping("/user/display") // view all user
    public ResponseEntity<?> display_user() {
        return new ResponseEntity<>(userService.show_user(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/update/{userId}") // update user
    public ResponseEntity<?> update_user(@PathVariable("userId") int userId, @RequestParam("img") MultipartFile fileUpload, UserDto userDto) throws IOException {

        User userToken = userRepo.findById(userId);

        if (userToken.getEmail().equalsIgnoreCase(authentication().getPrincipal().toString())) {
            userDto.setImg(fileUpload);
            userService.update_user(userId, userDto);
            userService.display_userId(userId);

            return new ResponseEntity<>("Update Profile Berhasil", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("blok", HttpStatus.BAD_GATEWAY);
        }

    }

    @PutMapping("/user/update-role/{userId}")
    public ResponseEntity<?> update_role(@PathVariable("userId") int userId, UserDto userDto) throws IOException {

        User userToken = userRepo.findById(userId);

        if (userToken.getEmail().equalsIgnoreCase(authentication().getPrincipal().toString())) {
            userService.update_role(userId);
            userService.display_userId(userId);
            return new ResponseEntity<>("Update Seller telah berhasil,\nAnda bisa menjual barang", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("blok", HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping("/user/display/{email}") // view user by email
    public ResponseEntity<?> user_display_byEmail(@PathVariable String email) {
        if (userService.display_userEmail(email) != null) {
            User users = userService.display_userEmail(email);
            return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Buyer/registrasi") //register
    public ResponseEntity<?> submit_user_buyer(@RequestBody BuyerDto buyerDto) {
        User userLogin = userLoginService.findByUsername(buyerDto.getEmail());
        if (userLogin != null) {
            return new ResponseEntity<>(userLogin, HttpStatus.BAD_REQUEST);
        } else {
            userLoginService.saveUserBuyer(buyerDto);
            return new ResponseEntity<>("Registrasi Berhasil", HttpStatus.CREATED);
        }
    }

    @GetMapping("/refresh-token")
    public void refreshTokenController(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC512("Binar".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String usernameDecode = decodedJWT.getSubject();
                User userLogin = userLoginService.findByUsername(usernameDecode);
                String accessToken = JWT.create()
                        .withSubject(userLogin.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 120 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userLogin.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> map = new HashMap<>();
                map.put("access_token", accessToken);
                map.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), map);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errorMap);
            }

        } else {
            throw new RuntimeException("refresh token is missing");
        }
    }

}
