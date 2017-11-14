package fr.emse.majeureinfo.springbootintro.web;


import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Room;
import fr.emse.majeureinfo.springbootintro.model.Status;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static fr.emse.majeureinfo.springbootintro.model.Status.OFF;
import static fr.emse.majeureinfo.springbootintro.model.Status.ON;

@RestController
@RequestMapping(value = "/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;
    //private final LightDao lightDao;


    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/{id}/switch-light")
    public LightDto switchLight(@PathVariable("id") Long id){
        Room room = roomDao.getOne(id);
        Light light = room.getLight();
        Status lightstatus = light.getStatus();
        light.setStatus(lightstatus == ON ? OFF : ON);
        LightDto lightDto = new LightDto(light);
        return lightDto;
    }
}

