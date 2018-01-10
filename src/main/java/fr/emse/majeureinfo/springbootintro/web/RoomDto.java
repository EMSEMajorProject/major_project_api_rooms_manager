package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Room;

public class RoomDto {

    private final Long id;
    private final String name;
    private final LightDto light;
    private final NoiseDto noise;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.light = room.getLight() == null ? null: new LightDto(room.getLight());
        this.noise = room.getNoise() == null ? null: new NoiseDto(room.getNoise());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LightDto getLight() {
        return light;
    }

    public NoiseDto getNoise() {
        return noise;
    }
}
