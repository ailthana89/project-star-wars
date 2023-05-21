package com.project.api.starwars.service;

import com.project.api.starwars.dto.PlanetaDTO;
import com.project.api.starwars.entity.Planeta;
import com.project.api.starwars.repository.PlanetaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository planetaRepository;

    public Planeta criarPlaneta(PlanetaDTO planetaDTO) {
        Planeta planeta = planetaDTO.toEntity();
        return planetaRepository.save(planeta);
    }

    public Planeta buscarPorId(Long id) {
        Optional<Planeta> optionalPlaneta = planetaRepository.findById(id);
        if (optionalPlaneta.isEmpty()) {
            throw new EntityNotFoundException("Planeta não encontrado com o ID informado");
        }
        return optionalPlaneta.get();
    }

    public List<Planeta> listarTodosPlanetas() {
        return planetaRepository.findAll();
    }

    public List<PlanetaDTO> buscarPorNome(String nome) {
        List<Planeta> planetas = planetaRepository.findByNomeContainingIgnoreCase(nome);
        return planetas.stream()
                .map(PlanetaDTO::new)
                .collect(Collectors.toList());
    }

    public void deletarPlaneta(Long id) throws EntityNotFoundException {
        Planeta planeta = buscarPorId(id);
        planetaRepository.delete(planeta);
    }
}
