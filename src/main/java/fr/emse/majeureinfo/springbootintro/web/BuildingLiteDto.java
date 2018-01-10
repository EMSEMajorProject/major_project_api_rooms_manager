package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;

import java.util.List;
import java.util.stream.Collectors;

public class BuildingLiteDto {

    private final Long id;
    private final String name;
    private final int nb_rooms;
    private final int nb_light_on;
    private final int nb_noise_on;

    public BuildingLiteDto(Long id, String name, Long nb_rooms, Long nb_light_on, Long nb_noise_on) {
        this.id = id;
        this.name = name;
        this.nb_rooms = nb_rooms.intValue();
        this.nb_light_on = nb_light_on.intValue();
        this.nb_noise_on = nb_noise_on.intValue();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNbRooms() {
        return nb_rooms;
    }

    public int getNbLightOn() {
        return nb_light_on;
    }

    public int getNbNoiseOn() {
        return nb_noise_on;
    }

}
