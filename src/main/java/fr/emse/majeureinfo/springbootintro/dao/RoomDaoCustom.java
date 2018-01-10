package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Room;

import java.util.List;

public interface RoomDaoCustom {

    List<Room> findRoomsWithOnLights();
    List<Room> findRoomsWithOffLights();
    List<Room> findRoomsWithOnRinger();
    List<Room> findRoomsWithOffRinger();


}

