package com.project.api.starwars.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "planetas")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(nullable = false)
    private String clima;
    @Column(nullable = false)
    private String terreno;

    private Long numFilms;


    public Planeta(){

    }

    public Planeta(Long id, String nome, String clima, String terreno, Long numFilms) {
        this.id = id;
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
        this.numFilms = numFilms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planeta planeta = (Planeta) o;
        return Objects.equals(id, planeta.id) && Objects.equals(nome, planeta.nome) && Objects.equals(clima, planeta.clima) && Objects.equals(terreno, planeta.terreno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, clima, terreno);
    }

    @Override
    public String toString() {
        return "Planeta{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", clima='" + clima + '\'' +
                ", terreno='" + terreno + '\'' +
                '}';
    }
}
