package com.ada.economizaapi;

import com.ada.economizaapi.controllers.MercadoController;
import com.ada.economizaapi.entities.Localizacao;
import com.ada.economizaapi.entities.Mercado;
import com.ada.economizaapi.entities.ProdutoPreco;
import com.ada.economizaapi.services.MercadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MercadoController.class)
public class MercadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MercadoService mercadoService;

    @Test
    public void testandoFindAll() throws Exception {
        given(mercadoService.findAll()).willReturn(Arrays.asList(new Mercado(), new Mercado()));

        mockMvc.perform(get("/mercado")
                        .with(user("user").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testandoFindById() throws Exception {
        Long id = 1L;
        Mercado mercado = new Mercado();
        mercado.setId(id);
        given(mercadoService.findById(id)).willReturn(Optional.of(mercado));

        mockMvc.perform(get("/mercado/{id}", id)
                        .with(user("user").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testandoPost() throws Exception {
        Mercado mercado = new Mercado();
        mercado.setId(1L);
        mercado.setNome("Mercado Teste");
        Localizacao localizacao = new Localizacao();
        localizacao.setId(1L);
        localizacao.setCoordenadas("Coordenadas Teste");
        mercado.setLocalizacao(localizacao);
        List<ProdutoPreco> produtoPrecos = new ArrayList<>();
        ProdutoPreco produtoPreco = new ProdutoPreco();
        produtoPreco.setId(1L);
        produtoPreco.setPreco(100.0);
        produtoPreco.setDataAtualizacao(LocalDate.now());
        produtoPrecos.add(produtoPreco);
        mercado.setProdutoPrecos(produtoPrecos);

        given(mercadoService.save(any(Mercado.class))).willReturn(mercado);

        mockMvc.perform(post("/mercado")
                        .with(user("admin").password("pass").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mercado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mercado.getId()));
    }

    @Test
    public void testandoUpdate() throws Exception {
        Long id = 1L;
        Mercado mercado = new Mercado();
        mercado.setId(1L);
        mercado.setNome("Mercado Teste");
        Localizacao localizacao = new Localizacao();
        localizacao.setId(1L);
        localizacao.setCoordenadas("Coordenadas Teste");
        mercado.setLocalizacao(localizacao);
        List<ProdutoPreco> produtoPrecos = new ArrayList<>();
        ProdutoPreco produtoPreco = new ProdutoPreco();
        produtoPreco.setId(1L);
        produtoPreco.setPreco(100.0);
        produtoPreco.setDataAtualizacao(LocalDate.now());
        produtoPrecos.add(produtoPreco);
        mercado.setProdutoPrecos(produtoPrecos);

        given(mercadoService.update(any(Long.class), any(Mercado.class))).willReturn(mercado);

        mockMvc.perform(put("/mercado/{id}", id)
                        .with(user("admin").password("pass").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mercado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mercado.getId()));
    }

    @Test
    public void testandoDelete() throws Exception {
        Long id = 1L;

        doNothing().when(mercadoService).deleteById(id);

        mockMvc.perform(delete("/mercado/{id}", id)
                        .with(user("admin").password("pass").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}










