package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Light;

import java.util.List;

public interface LightDaoCustom {

    List<Light> findOnLights();

}

