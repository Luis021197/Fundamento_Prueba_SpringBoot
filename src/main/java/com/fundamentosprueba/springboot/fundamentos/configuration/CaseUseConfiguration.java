package com.fundamentosprueba.springboot.fundamentos.configuration;

import com.fundamentosprueba.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosprueba.springboot.fundamentos.caseuse.GetUserImplement;
import com.fundamentosprueba.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
