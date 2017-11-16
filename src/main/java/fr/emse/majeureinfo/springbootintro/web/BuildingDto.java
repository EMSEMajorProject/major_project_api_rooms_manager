package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class BuildingDto {

    private final Long id;
    private final String name;
    private final List<RoomDto> rooms;

    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.rooms = building.getRooms() == null ? null: building.getRooms().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }
}
