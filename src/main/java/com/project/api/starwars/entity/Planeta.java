package com.project.api.starwars.entity;

import javax.persistence.*;


@Entity
@Table(name = "tb_planet")
public class Planeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private String Clima;
    private String Terrano;

    public Planeta(){}

    public Planeta(Long id, String name, String clima, String terrano) {
        Id = id;
        Name = name;
        Clima = clima;
        Terrano = terrano;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getClima() {
        return Clima;
    }

    public void setClima(String clima) {
        Clima = clima;
    }

    public String getTerrano() {
        return Terrano;
    }

    public void setTerrano(String terrano) {
        Terrano = terrano;
    }

    @Override
    public String toString() {
        return "Planeta{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Clima='" + Clima + '\'' +
                ", Terrano='" + Terrano + '\'' +
                '}';
    }
}
