package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Reading;

import java.util.List;

public interface ReadingDAO {

    List<Reading> getAllReadings();

    void saveReading(Reading reading);

    Reading getReading(int id);

    void deleteReading(int id);
}
