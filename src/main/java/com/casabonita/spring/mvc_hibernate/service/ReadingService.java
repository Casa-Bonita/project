package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Reading;

import java.util.List;

public interface ReadingService {

    List<Reading> getAllReadings();

    void saveReading(Reading reading, int meterNumber);

    Reading getReading(int id);

    void deleteReading(int id);
}
