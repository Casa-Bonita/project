package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Renter;

import java.util.List;

public interface RenterService {

    public List<Renter> getAllRenters();

    public void saveRenter(Renter renter);

    public Renter getRenter (int id);

    public void deleteRenter(int id);
}
