package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long> {
}

