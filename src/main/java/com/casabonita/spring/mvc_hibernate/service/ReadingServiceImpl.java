package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService{

//    @Autowired
    private final ReadingDAO readingDAO;

    public ReadingServiceImpl(ReadingDAO readingDAO) {
        this.readingDAO = readingDAO;
    }

    @Override
    @Transactional
    public List<Reading> getAllReadings() {

        return readingDAO.getAllReadings();
    }

    @Override
    @Transactional
    public void saveReading(Reading reading) {

        readingDAO.saveReading(reading);

    }

    @Override
    @Transactional
    public Reading getReading(int id) {

        return readingDAO.getReading(id);
    }

    @Override
    @Transactional
    public void deleteReading(int id) {

        readingDAO.deleteReading(id);

    }
}
