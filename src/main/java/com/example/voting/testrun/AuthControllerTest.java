package com.example.voting.testrun;

import com.example.voting.Exceptions.BadRequestException;
import com.example.voting.config.AsyncConfing;
import com.example.voting.controller.AuthController;
import com.example.voting.entity.User;
import com.example.voting.entity.Vote;
import com.example.voting.model.JwtRequest;
import com.example.voting.model.JwtResponse;
import com.example.voting.security.JwtHelper;
import com.example.voting.services.UserService;
import com.example.voting.services.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @BeforeEach
    void setUp() {
        UserDetailsService userDetailsService = new UserDetailsServiceStub();
        AuthenticationManager authenticationManager = new AuthenticationManagerStub();
        UserService userService = new UserService();
        VoteService voteService = new VoteService();
        JwtHelper jwtHelper = new JwtHelper();
        AsyncConfing asyncConfing = new AsyncConfing();

        authController = new AuthController();
        authController.setUserDetailsService(userDetailsService);
        authController.setManager(authenticationManager);
        authController.setUserService(userService);
        authController.setVoteService(voteService);
        authController.setHelper(jwtHelper);
        authController.setAsyncConfing(asyncConfing);
    }

    @Test
    void testLogin_SuccessfulAuthentication() {

        JwtRequest request = new JwtRequest("varun", "123");


        ResponseEntity<JwtResponse> response = authController.login(request);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getJwtToken());
        assertEquals("varun", response.getBody().getUsername());
    }

//    @Test
//    void testLogin_WithBadCredentialsException() {
//        JwtRequest request = new JwtRequest("username", "incorrect_password");
//
//        assertThrows(BadCredentialsException.class, () -> authController.login(request));
//    }

//    @Test
//    void testCreateUser() {
//
//        User user = new User();
//        user.setUsername("varun");
//        user.setPassword("123");
//
//
//        User result = authController.createUser(user);
//
//
//        assertNotNull(result);
//        assertEquals("varun", result.getUsername());
//        assertEquals("123", result.getPassword());
//    }

    @Test
    void testCreateUser_ExceptionThrown() {
        User user = new User();
        user.setUsername("varun");
        user.setPassword("123");

        assertThrows(ResponseStatusException.class, () -> authController.createUser(user));
    }

//    @Test
//    void testVoteNominee() {
//        Vote vote = new Vote();
//        vote.setFromId("f9888bc6-0ebf-4ddd-93fd-65e7ccd4ec70");
//        vote.setToId("6c60b798-da53-4a2d-8fc6-f2af0d8c1698");
//
//        Vote result = authController.voteNominee(vote);
//
//        assertNotNull(result);
//        assertEquals("f9888bc6-0ebf-4ddd-93fd-65e7ccd4ec70", result.getFromId());
//        assertEquals("6c60b798-da53-4a2d-8fc6-f2af0d8c1698", result.getToId());
//    }

    @Test
    void testVoteNominee_WithBadRequestException() {
        Vote vote = null;
        assertThrows(BadRequestException.class, () -> authController.voteNominee(vote));
    }

    @Test
    void testAsyncConfingInitialization() {
        assertNotNull(authController.asyncConfing);
    }


    @Test
    void testVoteNominee_ExceptionThrown() {
        Vote vote = new Vote();
        vote.setFromId("6c60b798-da53-4a2d-8fc6-f2af0d8c1698");
        vote.setToId("83c570df-44f5-4577-860b-a78fbf07edb7");
        vote.setId(1213);

        assertThrows(ResponseStatusException.class, () -> authController.voteNominee(vote));
    }

//    @Test
//    void testGetVotes() {
//        String username = "karan";
//
//        Integer result = authController.getVotes(username);
//
//        assertNotNull(result);
//        assertEquals(7, result);
//    }

    @Test
    void testGetVotes_WithBadRequestException() {
        String username = null;

        assertThrows(BadRequestException.class, () -> authController.getVotes(username));
    }

    @Test
    void testGetVotes_ExceptionThrown() {
        String username = "karan";

        assertThrows(ResponseStatusException.class, () -> authController.getVotes(username));
    }

    private static class UserDetailsServiceStub implements UserDetailsService {
        @Override
        public UserDetails loadUserByUsername(String username) {
            return new UserDetailsStub(username);
        }
    }

    private static class AuthenticationManagerStub implements AuthenticationManager {
        @Override
        public Authentication authenticate(Authentication authentication) {
            return null;
        }
    }


    private static class UserDetailsStub implements UserDetails {
        private final String username;

        UserDetailsStub(String username) {
            this.username = username;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }
}
