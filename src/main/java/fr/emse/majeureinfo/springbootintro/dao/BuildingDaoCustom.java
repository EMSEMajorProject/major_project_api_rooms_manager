package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Room;
import fr.emse.majeureinfo.springbootintro.web.BuildingLiteDto;

import java.util.List;

public interface BuildingDaoCustom {

    List<BuildingLiteDto> findBuildingLite();

}

