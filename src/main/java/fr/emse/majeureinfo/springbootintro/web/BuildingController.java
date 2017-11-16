package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.BuildingDao;
import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static fr.emse.majeureinfo.springbootintro.model.Status.OFF;
import static fr.emse.majeureinfo.springbootintro.model.Status.ON;


@RestController
@RequestMapping(value = "/api/buildings")
@Transactional
public class BuildingController {

    private final BuildingDao buildingDao;
    private final RoomDao roomDao;

    public BuildingController(BuildingDao buildingDao,RoomDao roomDao) {
        this.buildingDao = buildingDao;
        this.roomDao= roomDao;
    }

    @GetMapping
    public List<BuildingDto> list() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = {"/{buildingId}","/{buildingId}/context"})
    public BuildingDto get (@PathVariable Long buildingId){
        Building building = buildingDao.getOne(buildingId);
        return new BuildingDto(building);

    }

    @GetMapping(value = "/{buildingId}/rooms")
    public List<RoomDto> listRooms (@PathVariable Long buildingId){
        Building building = buildingDao.getOne(buildingId);
        return building.getRooms().stream().map(RoomDto::new).collect(Collectors.toList());
    }
    
    @PostMapping(value = "/{buildingId}/rooms/{roomId}/switch-light")
    public LightDto switchLight(@PathVariable("roomId") Long roomId, @PathVariable("buildingId") Long buildingId){
        Room room = roomDao.getOne(roomId);
        Light light = room.getLight();
        light.setStatus(light.getStatus() == ON ? OFF : ON);
        return new LightDto(light);
    }

    @PostMapping(value = "/{buildingId}/rooms/{roomId}/switch-noise")
    public NoiseDto switchNoise(@PathVariable("roomId") Long roomId, @PathVariable("buildingId") Long buildingId){
        Room room = roomDao.getOne(roomId);
        Noise noise = room.getNoise();
        noise.setStatus(noise.getStatus() == ON ? OFF : ON);
        return new NoiseDto(noise);
    }

//    @PostMapping(value = "/{buildingId}/rooms/{roomId}/delete")
//    public void deleteRoom(@PathVariable("roomId") Long roomId, @PathVariable("buildingId") Long buildingId){
//        Room room = roomDao.getOne(roomId);
//        Noise noise = room.getNoise();
//        noise.setStatus(noise.getStatus() == ON ? OFF : ON);
//        return new NoiseDto(noise);
//    }

    @PostMapping(value = "/{id}/delete")
    public void delete(@PathVariable("id") Long id){
        buildingDao.delete(id);
    }

    @PostMapping(value = "/new")
    public BuildingDto newBuilding(@RequestBody Building building){
        buildingDao.save(building);
        return new BuildingDto(building);
    }
}

