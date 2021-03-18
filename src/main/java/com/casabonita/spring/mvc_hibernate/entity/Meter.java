package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="meter")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="meter_number")
    private int meterNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="place_id")
    private Place meterPlace;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "meter")
    private List<MeterData> meterDatas;

    public Meter() {
    }

    public Meter(int meterNumber) {
        this.meterNumber = meterNumber;
    }

    public void addMeterDataToMeter(MeterData meterData){
        if(meterDatas == null){
            meterDatas = new ArrayList<>();
        }
        meterDatas.add(meterData);
        meterData.setMeter(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(int meterNumber) {
        this.meterNumber = meterNumber;
    }

    public Place getMeterPlace() {
        return meterPlace;
    }

    public void setMeterPlace(Place meterPlace) {
        this.meterPlace = meterPlace;
    }
}
