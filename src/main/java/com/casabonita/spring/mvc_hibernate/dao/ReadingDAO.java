package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.MeterData;

import java.util.List;

public interface ReadingDAO {

    public List<MeterData> getAllReadings();

    public void saveReading(MeterData meterData);

    public MeterData getReading(int id);

    public void deleteReading(int id);
}
