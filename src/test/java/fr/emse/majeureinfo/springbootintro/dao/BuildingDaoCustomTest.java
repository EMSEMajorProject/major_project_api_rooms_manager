package fr.emse.majeureinfo.springbootintro.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import fr.emse.majeureinfo.springbootintro.web.BuildingLiteDto;
import fr.emse.majeureinfo.springbootintro.model.Status;
import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/test.properties")
public class BuildingDaoCustomTest {

    @Autowired
    private BuildingDao buildingDao;


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    protected static final DbSetupTracker TRACKER = new DbSetupTracker();

    private static final Operation DELETE_ALL = DeleteAll.from("BUILDING_ROOMS","BUILDING","ROOM","LIGHT","NOISE");

    protected void dbSetup(Operation operation) {
        DbSetup setup = new DbSetup(new DataSourceDestination(dataSource),
                Operations.sequenceOf(DELETE_ALL, operation));
        TRACKER.launchIfNecessary(setup);
    }

    @Before
    public void prepare() {
        Operation lighton =
                Insert.into("LIGHT")
                        .columns("id", "level", "status")
                        .values(1L, 22, Status.ON)
                        .build();
        Operation lightoff =
                Insert.into("LIGHT")
                        .columns("id", "level", "status")
                        .values(2L, 22, Status.OFF)
                        .build();
        Operation noiseon =
                Insert.into("NOISE")
                        .columns("id", "level", "status")
                        .values(1L, 22, Status.ON)
                        .build();
        Operation noiseoff =
                Insert.into("NOISE")
                        .columns("id", "level", "status")
                        .values(2L, 22, Status.OFF)
                        .build();
        Operation building =
                Insert.into("BUILDING")
                        .columns("id", "name")
                        .values(1L, "Ada Lovelace")
                        .build();
        Operation room =
                Insert.into("ROOM")
                        .columns("id","name", "light_id", "noise_id")
                        .values(1L,"lightOn", 1L, 1L)
                        .build();
        Operation room2 =
                Insert.into("ROOM")
                        .columns("id","name", "light_id", "noise_id")
                        .values(2L,"lightOff", 2L, 2L)
                        .build();
        Operation building_room1 =
                Insert.into("BUILDING_ROOMS")
                        .columns("BUILDING_ID", "ROOMS_ID")
                        .values(1L, 1L)
                        .build();
        Operation building_room2 =
                Insert.into("BUILDING_ROOMS")
                        .columns("BUILDING_ID", "ROOMS_ID")
                        .values(1L, 2L)
                        .build();
        dbSetup(Operations.sequenceOf(lighton, lightoff, noiseon, noiseoff, building, room, room2, building_room1, building_room2));
    }

    @Test
    public void shouldFindWithCountedRooms() {
        TRACKER.skipNextLaunch();
        List<BuildingLiteDto> dtos = buildingDao.findBuildingLite();
        assertThat(dtos).extracting(BuildingLiteDto::getNbLightOn).containsExactly(1);
        assertThat(dtos).extracting(BuildingLiteDto::getNbNoiseOn).containsExactly(1);
        assertThat(dtos).extracting(BuildingLiteDto::getNbRooms).containsExactly(2);
    }


}