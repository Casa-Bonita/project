package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeterServiceImpl implements MeterService{

    private final MeterDAO meterDAO;
    private final PlaceDAO placeDAO;

    public MeterServiceImpl(MeterDAO meterDAO, PlaceDAO placeDAO) {
        this.meterDAO = meterDAO;
        this.placeDAO = placeDAO;
    }

    @Override
    @Transactional
    public List<Meter> getAllMeters() {

        return meterDAO.getAllMeters();
    }

    @Override
    @Transactional
    public void saveMeter(Meter meter, int meterPlaceNumber) {

        Place place = placeDAO.getPlaceByNumber(meterPlaceNumber);

        meter.setMeterPlace(place);

        meterDAO.saveMeter(meter);
    }

    @Override
    @Transactional
    public Meter getMeter(int id) {

        return meterDAO.getMeter(id);
    }

    @Override
    @Transactional
    public void deleteMeter(int id) {

        meterDAO.deleteMeter(id);
    }

    @Override
    @Transactional
    public Meter getMeterByNumber(int number) {

        return meterDAO.getMeterByNumber(number);
    }
}
