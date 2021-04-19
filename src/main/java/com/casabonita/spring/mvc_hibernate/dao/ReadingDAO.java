package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Reading;

import java.util.List;

public interface ReadingDAO {

    public List<Reading> getAllReadings();

    public void saveReading(Reading reading);

    public Reading getReading(int id);

    public void deleteReading(int id);
}
