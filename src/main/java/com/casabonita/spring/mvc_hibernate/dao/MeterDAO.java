package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Meter;

import java.util.List;

public interface MeterDAO {

    List<Meter> getAllMeters();

    void saveMeter(Meter meter);

    Meter getMeter(Integer id);

    Meter getMeterByPlaceId(Integer id);

    Meter getMeterByNumber(String number);

    void deleteMeterById(Integer id);
}
