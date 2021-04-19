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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name="place_id")
    private Place meterPlace;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meter")
    private List<Reading> readingsList;

    public Meter() {
    }

    public Meter(int number, Place meterPlace) {
        this.number = number;
        this.meterPlace = meterPlace;
    }

    public void addReadingToMeter(Reading reading){
        if(readingsList == null){
            readingsList = new ArrayList<>();
        }
        readingsList.add(reading);
        reading.setMeter(this);
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

    public List<Reading> getReadingsList() {
        return readingsList;
    }

    public void setReadingsList(List<Reading> readingsList) {
        this.readingsList = readingsList;
    }
}
