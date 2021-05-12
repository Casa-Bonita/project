package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Meter;

import java.util.List;

public interface MeterService {

    List<Meter> getAllMeters();

    void saveMeter(Meter meter, int meterPlaceNumber);

    Meter getMeter(int id);

    void deleteMeter(int id);

    Meter getMeterByNumber(int number);

}
