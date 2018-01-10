package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Noise;
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

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = {"/{roomId}","/{roomId}/context"})
    public RoomDto get (@PathVariable Long roomId){
        Room room = roomDao.getOne(roomId);
        return new RoomDto(room);

    }
    
    @PostMapping(value = "/{id}/switch-light")
    public LightDto switchLight(@PathVariable("id") Long id){
        Room room = roomDao.getOne(id);
        Light light = room.getLight();
        light.setStatus(light.getStatus() == ON ? OFF : ON);
        return new LightDto(light);
    }

    @PostMapping(value = "/{id}/switch-noise")
    public NoiseDto switchNoise(@PathVariable("id") Long id){
        Room room = roomDao.getOne(id);
        Noise noise = room.getNoise();
        noise.setStatus(noise.getStatus() == ON ? OFF : ON);
        return new NoiseDto(noise);
    }

    @PostMapping(value = "/{id}/delete")
    public void delete(@PathVariable("id") Long id){
        roomDao.delete(id);
    }

    @PostMapping(value = "/new")
    public RoomDto newRoom(@RequestBody Room room){
        roomDao.save(room);
        return new RoomDto(room);
    }

    @GetMapping(value ="/list-with-on-lights")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findRoomsWithOnLights().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value ="/list-with-off-lights")
    public List<RoomDto> listWithOffLight() {
        return roomDao.findRoomsWithOffLights().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value ="/list-with-on-ringer")
    public List<RoomDto> listWithOnRinger() {
        return roomDao.findRoomsWithOffLights().stream().map(RoomDto::new).collect(Collectors.toList());
    }
    @GetMapping(value ="/list-with-off-ringer")
    public List<RoomDto> listWithOffRinger() {
        return roomDao.findRoomsWithOffLights().stream().map(RoomDto::new).collect(Collectors.toList());
    }
    @PostMapping(value = "/all/switch-light/{state}")
    public List<RoomDto> switchLight(@PathVariable String state) {
        List<Light> allLightswitched= roomDao.findAll().stream().filter(r -> r.getLight().getStatus()==((state.equals("ON")) ? Status.ON : Status.OFF)).map(r -> r.getLight().switchlight()).collect(Collectors.toList());
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/all/switch-ringer/{state}")
    public List<RoomDto> switchRinger(@PathVariable String state) {
        List<Noise> allRingerswitched= roomDao.findAll().stream().filter(r -> r.getNoise().getStatus()==((state.equals("ON")) ? Status.ON : Status.OFF)).map(r -> r.getNoise().switchringer()).collect(Collectors.toList());
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}

