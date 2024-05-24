package com.ada.economizaapi;

import com.ada.economizaapi.security.AuthenticationConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationConfigTest {

    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    public void testandoServicoDetalhesUsuarioComCodificador() {
        MockitoAnnotations.openMocks(this);
        when(encoder.encode(anyString())).thenReturn("mockEncodedPassword");

        AuthenticationConfig config = new AuthenticationConfig();
        UserDetailsService service = config.userDetailsService(encoder);

        assertNotNull(service);
        assertTrue(service instanceof InMemoryUserDetailsManager);

        verify(encoder, times(2)).encode(anyString());
    }

    @Test
    public void testandoCodificadorDeSenha() {
        AuthenticationConfig config = new AuthenticationConfig();
        BCryptPasswordEncoder encoder = config.passwordEncoder();

        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }
}




