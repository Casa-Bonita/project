package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.MeterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService{

    @Autowired
    private ReadingDAO readingDAO;

    @Override
    @Transactional
    public List<MeterData> getAllReadings() {

        return readingDAO.getAllReadings();
    }

    @Override
    @Transactional
    public void saveReading(MeterData meterData) {

        readingDAO.saveReading(meterData);

    }

    @Override
    @Transactional
    public MeterData getReading(int id) {

        return readingDAO.getReading(id);
    }

    @Override
    @Transactional
    public void deleteReading(int id) {

        readingDAO.deleteReading(id);

    }
}
