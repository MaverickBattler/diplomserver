package edu.leti.diplomserver.service;

import edu.leti.diplomserver.domain.User;
import edu.leti.diplomserver.dto.AuthenticationRequestDto;
import edu.leti.diplomserver.repository.UserRepository;
import edu.leti.diplomserver.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Сервис, в котором представлены методы, связанные с аутентификацией
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    //Constructor Dependency Injection
    public AuthenticationService(UserRepository userRepository,
                                 AuthenticationManager authenticationManager,
                                 JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    //Аутентифицирует пользователя по email и паролю
    public String authenticate(AuthenticationRequestDto authenticationRequestDto) {
        try {
            //Получение электронной почты пациента из DTO
            String email = authenticationRequestDto.getEmail();
            //Аутентификация, которая может выбросить AuthenticationException
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,
                    authenticationRequestDto.getPassword()));
            //Нахождение в UserRepository пользователя с данным email
            User user = userRepository.findByEmail(email);
            if (user == null) { //Если пользователь не был найден
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }
            //Создается токен аутентификации и возвращается
            return jwtTokenProvider.createToken(email, user.getRoles());
        } catch (AuthenticationException e) { //При ошибке аутентификации
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}