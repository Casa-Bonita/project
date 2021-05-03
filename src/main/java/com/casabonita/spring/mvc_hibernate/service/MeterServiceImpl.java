package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeterServiceImpl implements MeterService{

//    @Autowired
    private final MeterDAO meterDAO;

    public MeterServiceImpl(MeterDAO meterDAO) {
        this.meterDAO = meterDAO;
    }

    @Override
    @Transactional
    public List<Meter> getAllMeters() {

        return meterDAO.getAllMeters();
    }

    @Override
    @Transactional
    public void saveMeter(Meter meter) {

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
}
