package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Renter;

import java.util.List;

public interface RenterDAO {

    List<Renter> getAllRenters();

    void saveRenter(Renter renter);

    Renter getRenter(int id);

    void deleteRenter(int id);
}
