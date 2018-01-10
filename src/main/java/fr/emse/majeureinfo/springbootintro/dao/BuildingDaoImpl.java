package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Status;
import fr.emse.majeureinfo.springbootintro.web.BuildingLiteDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BuildingLiteDto> findBuildingLite() {
        String jpql = "select new "+
                "   fr.emse.majeureinfo.springbootintro.web.BuildingLiteDto(" +
                " bd.id , " +
                " bd.name," +
                "count(distinct rooms.id) as nb, " +
                "count(distinct roomsWithLightOn.id) as nbWithLightsOn, " +
                "count(distinct roomsWithNoiseOn.id) as nbWithNoisesOn " +
                "   ) " +
                "from Building bd " +
                "inner join bd.rooms rooms " +
                "left outer join bd.rooms roomsWithLightOn " +
                "left outer join roomsWithLightOn.light light " +
                "left outer join bd.rooms roomsWithNoiseOn " +
                "left outer join roomsWithNoiseOn.noise noise " +
                "where light.status = :statusOn and noise.status = :statusOn " +
                "group by bd.id ";

        List<BuildingLiteDto> dtos = em.createQuery(jpql, BuildingLiteDto.class)
                .setParameter("statusOn", Status.ON)
                .getResultList();

        return dtos;
    }
}

