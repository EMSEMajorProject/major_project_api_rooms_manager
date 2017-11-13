package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Room;
import fr.emse.majeureinfo.springbootintro.model.Status;

public class RoomDto {

    private final Long id;
    private final Light light;
    private final Noise noise;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.light = room.getLight();
        this.noise = room.getNoise();
    }

    public Long getId() {
        return id;
    }

    public Light getLight() {
        return light;
    }

    public Noise getNoise() {
        return noise;
    }
}
