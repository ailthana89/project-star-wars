package com.project.api.starwars.repository;

import com.project.api.starwars.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

    List<Planeta> findByNomeContainingIgnoreCase(String nome);

}
