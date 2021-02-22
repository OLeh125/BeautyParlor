package org.fp.beauty.parlor.model.dao;

import org.fp.beauty.parlor.model.entity.Admin;

public interface AdminDao extends Dao<Admin>{
    boolean exists(String name,String surname, String password);
}
