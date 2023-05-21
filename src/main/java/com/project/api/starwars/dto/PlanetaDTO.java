package com.project.api.starwars.dto;

import com.project.api.starwars.entity.Planeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class PlanetaDTO {
    private Long id;

    private String nome;
    @NotBlank(message = "Clima é obrigatório")
    private String clima;
    @NotBlank(message = "Terreno é obrigatório")
    private String terreno;

    private Long numFilms;

    public PlanetaDTO() {
    }

    public PlanetaDTO(Planeta planeta) {
        this.id = planeta.getId();
        this.nome = planeta.getNome();
        this.clima = planeta.getClima();
        this.terreno = planeta.getTerreno();
        this.numFilms = planeta.getNumFilms();
    }

    @Positive(message = "ID deve ser maior que zero")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public Long getNumFilms() {
        return numFilms;
    }

    public void setNumFilms(Long numFilms) {
        this.numFilms = numFilms;
    }

    public Planeta toEntity() {
        return new Planeta(id, nome, clima, terreno, numFilms);
    }
}
