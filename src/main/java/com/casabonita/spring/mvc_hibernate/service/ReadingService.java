package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.MeterData;

import java.util.List;

public interface ReadingService {

    public List<MeterData> getAllReadings();

    public void saveReading(MeterData meterData);

    public MeterData getReading(int id);

    public void deleteReading(int id);
}
