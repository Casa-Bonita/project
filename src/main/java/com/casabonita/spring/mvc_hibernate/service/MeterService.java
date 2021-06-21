package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Meter;

import java.util.List;

public interface MeterService {

    List<Meter> getAllMeters();

    void saveMeter(Meter meter, int meterPlaceNumber);

    Meter getMeter(Integer id);

    Meter getMeterByPlaceId(Integer id);

    Meter getMeterByNumber(String number);

    void deleteMeterById(Integer id);
}
