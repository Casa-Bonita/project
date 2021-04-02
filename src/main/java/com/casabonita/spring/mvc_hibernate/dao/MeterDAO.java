package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Meter;

import java.util.List;

public interface MeterDAO {

    public List<Meter> getAllMeters();

    public void saveMeter(Meter meter);

    public Meter getMeter(int id);

    public void deleteMeter(int id);
}
