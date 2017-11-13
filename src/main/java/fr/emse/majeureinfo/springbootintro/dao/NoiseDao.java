package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Noise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoiseDao extends JpaRepository<Noise, Long> {
}

