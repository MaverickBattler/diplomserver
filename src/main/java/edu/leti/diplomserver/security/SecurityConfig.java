package edu.leti.diplomserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //Конфигурация безопасности сети
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    //Отключение Basic Authentication
                .httpBasic().disable()
                //Отключение защиты от CSRF
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //Настройка необходимости аутентификации запросов
                .authorizeRequests()
                //Регистрация нового пользователя
                .antMatchers("/register").permitAll()
                //Вход в аккаунт
                .antMatchers("/login").permitAll()
                //Проверка наличия пациента с данным ID медицинской карты
                .antMatchers("/verify-patient-id").permitAll()
                //Подтверждение личности пациента через e-mail
                .antMatchers("/verify-email").permitAll()
                //Остальные запросы должны быть аутентифицированы
                .anyRequest().authenticated()
                .and()
                //Установка JwtConfigurer
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
