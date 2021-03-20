package com.vis.test.controller;

import com.vis.test.dto.LoginDto;
import com.vis.test.dto.TokenDto;
import com.vis.test.service.auth.UserDetailsServiceImpl;
import com.vis.test.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("login")
    public TokenDto login(@RequestBody LoginDto loginDto) throws Exception{
        authenticate(loginDto.getUsername(), loginDto.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginDto.getUsername());
        final String token = jwtTokenUtils.generateToken(userDetails);
        return new TokenDto(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
