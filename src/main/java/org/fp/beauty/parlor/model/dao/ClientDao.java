package org.fp.beauty.parlor.model.dao;


import org.fp.beauty.parlor.model.entity.Client;

public interface ClientDao extends Dao<Client> {
    boolean exists(String name, String surname, String password);
    int findClientIdByNameSurnameAndPassword(String name, String surname, String password);
}
