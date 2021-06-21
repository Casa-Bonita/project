package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeterServiceImpl implements MeterService{

    private final MeterDAO meterDAO;
    private final PlaceDAO placeDAO;
    private final ReadingDAO readingDAO;

    public MeterServiceImpl(MeterDAO meterDAO, PlaceDAO placeDAO, ReadingDAO readingDAO) {
        this.meterDAO = meterDAO;
        this.placeDAO = placeDAO;
        this.readingDAO = readingDAO;
    }

    @Override
    @Transactional
    public List<Meter> getAllMeters() {

        return meterDAO.getAllMeters();
    }

    @Override
    @Transactional
    public void saveMeter(Meter meter, int meterPlaceNumber) {

        Meter meterToSave;

        if(meter.getId() == null){
            meterToSave = new Meter();
        }
        else{
            meterToSave = meterDAO.getMeter(meter.getId());
        }

        meterToSave.setNumber(meter.getNumber());

        Place place = placeDAO.getPlaceByNumber(meterPlaceNumber);
        meterToSave.setMeterPlace(place);

        meterDAO.saveMeter(meterToSave);
    }

    @Override
    @Transactional
    public Meter getMeter(Integer id) {

        return meterDAO.getMeter(id);
    }

    @Override
    @Transactional
    public Meter getMeterByPlaceId(Integer id) {

        return meterDAO.getMeterByPlaceId(id);
    }

    @Override
    @Transactional
    public Meter getMeterByNumber(String number) {

        return meterDAO.getMeterByNumber(number);
    }

    @Override
    @Transactional
    public void deleteMeterById(Integer id) {

        readingDAO.deleteReadingByMeterId(id);
        meterDAO.deleteMeterById(id);
    }
}
