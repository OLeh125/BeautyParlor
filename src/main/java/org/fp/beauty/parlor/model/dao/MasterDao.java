package org.fp.beauty.parlor.model.dao;


import org.fp.beauty.parlor.model.entity.Master;

import java.util.List;

public interface MasterDao extends Dao<Master>{
    int findMastersIdByNameAndSurname(String name, String surname);
    boolean exists(String name, String surname, String password);
    List<Master> findAllMastersByTreatmentNameAndPrice(String name, int price);
}
