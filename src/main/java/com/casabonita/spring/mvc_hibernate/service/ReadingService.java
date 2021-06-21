package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Reading;

import java.util.List;

public interface ReadingService {

    List<Reading> getAllReadings();

    void saveReading(Reading reading, String meterNumber);

    Reading getReading(Integer id);

    void deleteReadingById(Integer id);

    void deleteReadingByMeterId(Integer id);
}
