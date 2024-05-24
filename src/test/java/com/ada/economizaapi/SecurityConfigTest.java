package com.ada.economizaapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SecurityConfigTest {

    @Qualifier("configure")
    @Autowired
    private SecurityFilterChain securityFilterChain;

    @Test
    public void testeDeFiltroDeSegurança() {
        assertThat(securityFilterChain).isNotNull();
    }

}















