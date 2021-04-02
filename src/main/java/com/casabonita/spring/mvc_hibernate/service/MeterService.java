package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Meter;

import java.util.List;

public interface MeterService {

    public List<Meter> getAllMeters();

    public void saveMeter(Meter meter);

    public Meter getMeter(int id);

    public void deleteMeter(int id);

}
