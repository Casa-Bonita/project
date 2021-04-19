package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Reading;

import java.util.List;

public interface ReadingService {

    public List<Reading> getAllReadings();

    public void saveReading(Reading reading);

    public Reading getReading(int id);

    public void deleteReading(int id);
}
