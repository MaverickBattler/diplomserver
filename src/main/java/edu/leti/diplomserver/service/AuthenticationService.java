package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.AuthenticationRequestDto;
import edu.leti.diplomserver.repository.UnverifiedUsersRepository;
import edu.leti.diplomserver.repository.UserRepository;
import edu.leti.diplomserver.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(UserRepository userRepository,
                                 AuthenticationManager authenticationManager,
                                 JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String authenticate(AuthenticationRequestDto authenticationRequestDto) {
        try {
            String email = authenticationRequestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,
                    authenticationRequestDto.getPassword()));
            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            return jwtTokenProvider.createToken(email, user.getRoles());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}