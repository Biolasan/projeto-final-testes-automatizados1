package com.ada.economizaapi.controllers;

import com.ada.economizaapi.services.MercadoService;
import com.ada.economizaapi.entities.Mercado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercado")
public class MercadoController {

    @Autowired
    private MercadoService mercadoService;

    @GetMapping()
    public ResponseEntity<List<Mercado>> findAll() {
        List<Mercado> mercados = mercadoService.findAll();
        return new ResponseEntity<>(mercados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mercado> findById(@PathVariable Long id) {
        Optional<Mercado> mercado = mercadoService.findById(id);
        return mercado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Mercado> post(@RequestBody Mercado mercado) {
        Mercado savedMercado = mercadoService.save(mercado);
        return new ResponseEntity<>(savedMercado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mercado> update(@PathVariable Long id, @RequestBody Mercado mercado) {
        Mercado updatedMercado = mercadoService.update(id, mercado);
        return new ResponseEntity<>(updatedMercado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mercadoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

