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
    private int number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="place_id")
    private Place meterPlace;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "meter")
    private List<MeterData> meterDatasList;

    public Meter() {
    }

    public Meter(int number, Place meterPlace) {
        this.number = number;
        this.meterPlace = meterPlace;
    }

    public void addMeterDataToMeter(MeterData meterData){
        if(meterDatasList == null){
            meterDatasList = new ArrayList<>();
        }
        meterDatasList.add(meterData);
        meterData.setMeter(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Place getMeterPlace() {
        return meterPlace;
    }

    public void setMeterPlace(Place meterPlace) {
        this.meterPlace = meterPlace;
    }

    public List<MeterData> getMeterDatasList() {
        return meterDatasList;
    }

    public void setMeterDatasList(List<MeterData> meterDatasList) {
        this.meterDatasList = meterDatasList;
    }
}
