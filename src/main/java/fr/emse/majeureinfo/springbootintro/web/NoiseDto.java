package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Status;

public class NoiseDto {

    private final Long id;
    private final Integer level;
    private final Status status;

    public NoiseDto(Noise noise) {
        this.id = noise.getId();
        this.level = noise.getLevel();
        this.status = noise.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }
}
