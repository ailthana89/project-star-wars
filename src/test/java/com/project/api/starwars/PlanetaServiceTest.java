package com.project.api.starwars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.project.api.starwars.dto.PlanetaDTO;
import com.project.api.starwars.entity.Planeta;
import com.project.api.starwars.repository.PlanetaRepository;

import com.project.api.starwars.service.PlanetaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlanetaServiceTest {

    @Mock
    private PlanetaRepository planetaRepository;

    @InjectMocks
    private PlanetaService planetaService;

    private Planeta planeta;
    private PlanetaDTO planetaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        planeta = new Planeta();
        planeta.setNome("Tatooine");
        planeta.setClima("Árido");
        planeta.setTerreno("Deserto");

        planetaDTO = new PlanetaDTO(planeta);
    }

    @Test
    void testCriarPlaneta() {
        when(planetaRepository.save(any(Planeta.class))).thenReturn(planeta);

        Planeta planetaSalvo = planetaService.criarPlaneta(planetaDTO);

        assertThat(planetaSalvo).isEqualTo(planeta);
    }

    @Test
    void testBuscarPorId() {
        when(planetaRepository.findById(any(Long.class))).thenReturn(Optional.of(planeta));

        Planeta planetaEncontrado = planetaService.buscarPorId(1L);

        assertThat(planetaEncontrado).isEqualTo(planeta);
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(planetaRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> planetaService.buscarPorId(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Planeta não encontrado com o ID informado");
    }

    @Test
    void testListarTodosPlanetas() {
        when(planetaRepository.findAll()).thenReturn(Arrays.asList(planeta));

        List<Planeta> planetas = planetaService.listarTodosPlanetas();

        assertThat(planetas).hasSize(1);
        assertThat(planetas.get(0)).isEqualTo(planeta);
    }

    @Test
    void testBuscarPorNome() {
        when(planetaRepository.findByNomeContainingIgnoreCase(any(String.class))).thenReturn(Arrays.asList(planeta));

        List<PlanetaDTO> planetas = planetaService.buscarPorNome("tat");

        assertThat(planetas).hasSize(1);
        assertThat(planetas.get(0).getNome()).isEqualTo(planeta.getNome());
    }

    @Test
    public void testDeletarPlaneta() throws EntityNotFoundException {
        // Given
        Long id = 1L;
        Planeta planeta = new Planeta();
        planeta.setId(id);
        when(planetaRepository.findById(id)).thenReturn(Optional.of(planeta));

        // When
        planetaService.deletarPlaneta(id);

        // Then
        verify(planetaRepository, times(1)).delete(planeta);
    }

    @Test
    public void testDeletarPlanetaComIdInvalido() {
        // Given
        Long id = 1L;
        when(planetaRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            planetaService.deletarPlaneta(id);
        });
        verify(planetaRepository, times(0)).delete(any(Planeta.class));
    }
}

