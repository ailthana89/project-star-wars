package com.project.api.starwars.controller;

import com.project.api.starwars.dto.PlanetaDTO;
import com.project.api.starwars.entity.Planeta;
import com.project.api.starwars.service.PlanetaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;

    private final WebClient webClient;

    public PlanetaController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://swapi.dev/api").build();
    }

    @PostMapping
    public ResponseEntity<Planeta> criarPlaneta(@RequestBody PlanetaDTO planetaDTO) {
        try {
            Planeta planeta = planetaService.criarPlaneta(planetaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(planeta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public Mono<Planeta> buscarPorId(@PathVariable Long id) {
        Planeta planeta = planetaService.buscarPorId(id);
        return webClient.get().uri("/planets/{id}", id).retrieve().bodyToMono(PlanetaDTO.class)
                .map(planetaDTO -> {
                    planeta.setNumFilms(planetaDTO.getNumFilms());
                    return planeta;
                });
    }

    @GetMapping
    public ResponseEntity<List<Planeta>> listarTodosPlanetas() {
        List<Planeta> planetas = planetaService.listarTodosPlanetas();
        return ResponseEntity.ok(planetas);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<List<PlanetaDTO>> buscarPorNome(@RequestParam String nome) {
        List<PlanetaDTO> planetas = planetaService.buscarPorNome(nome);
        return ResponseEntity.ok(planetas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlaneta(@PathVariable Long id) {
        try {
            planetaService.deletarPlaneta(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

