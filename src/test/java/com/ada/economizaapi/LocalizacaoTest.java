package com.ada.economizaapi;

import com.ada.economizaapi.entities.Localizacao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocalizacaoTest {

    @Test
    public void testandoConstrutorSemArgumentos() {
        Localizacao localizacao = new Localizacao();
        assertNotNull(localizacao);
    }

    @Test
    public void testandoConstrutorComTodosOsArgumentos() {
        Localizacao localizacao = new Localizacao(1L, "Coordenadas Teste");
        assertNotNull(localizacao);
        assertEquals(1L, localizacao.getId());
        assertEquals("Coordenadas Teste", localizacao.getCoordenadas());
    }

    @Test
    public void testandoGettersAndSetters() {
        Localizacao localizacao = new Localizacao();
        localizacao.setId(1L);
        localizacao.setCoordenadas("Coordenadas Teste");

        assertEquals(1L, localizacao.getId());
        assertEquals("Coordenadas Teste", localizacao.getCoordenadas());
    }

    @Test
    public void testandoToString() {
        Localizacao localizacao = new Localizacao(1L, "Coordenadas Teste");
        String expected = "Localizacao(id=1, coordenadas=Coordenadas Teste)";
        assertEquals(expected, localizacao.toString());
    }

    @Test
    public void testandoEqualsAndHashCode() {
        Localizacao localizacao1 = new Localizacao(1L, "Coordenadas Teste");
        Localizacao localizacao2 = new Localizacao(1L, "Coordenadas Teste");
        Localizacao localizacao3 = new Localizacao(2L, "Coordenadas Diferentes");

        assertEquals(localizacao1, localizacao2);
        assertNotEquals(localizacao1, localizacao3);

        assertEquals(localizacao1.hashCode(), localizacao2.hashCode());
        assertNotEquals(localizacao1.hashCode(), localizacao3.hashCode());
    }

    @Test
    public void testandoEqualsDoMesmoObjeto() {
        Localizacao localizacao = new Localizacao(1L, "Coordenadas Teste");
        assertEquals(localizacao, localizacao);
    }

    @Test
    public void testandoEqualsDoObjetoNulo() {
        Localizacao localizacao = new Localizacao(1L, "Coordenadas Teste");
        assertNotEquals(localizacao, null);
    }

    @Test
    public void testandoEqualsClasseDiferente() {
        Localizacao localizacao = new Localizacao(1L, "Coordenadas Teste");
        String otherClassObject = "Different Class Object";
        assertNotEquals(localizacao, otherClassObject);
    }

    @Test
    public void testandoHashCodeQuandoIdEhNulo() {
        Localizacao localizacao = new Localizacao(null, "Coordenadas Teste");
        assertEquals(0, localizacao.hashCode());
    }

    @Test
    public void testandoConstrutorComParametros() {
        Localizacao localizacao = new Localizacao("Coordenadas Teste");
        assertEquals("Coordenadas Teste", localizacao.getCoordenadas());
    }

    @Test
    public void testandoMockito() {
        Localizacao localizacaoMock = Mockito.mock(Localizacao.class);
        when(localizacaoMock.getCoordenadas()).thenReturn("Coordenadas Teste");

        assertEquals("Coordenadas Teste", localizacaoMock.getCoordenadas());
        verify(localizacaoMock, times(1)).getCoordenadas();
    }
}





