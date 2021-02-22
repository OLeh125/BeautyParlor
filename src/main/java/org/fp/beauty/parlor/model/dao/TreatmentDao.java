package org.fp.beauty.parlor.model.dao;


import org.fp.beauty.parlor.model.entity.Treatment;

import java.util.List;

public interface TreatmentDao {
    List<Treatment> findAll();
    int findIdByNameAndPrice(String name,int price);
    List<Treatment> findAllTreatmentsByMasterNameAndSurname(String name,String surname);
}
