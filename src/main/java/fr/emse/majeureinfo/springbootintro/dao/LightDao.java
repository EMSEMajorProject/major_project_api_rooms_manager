package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightDao extends JpaRepository<Light, Long>, LightDaoCustom {
}

