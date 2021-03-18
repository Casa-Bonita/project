package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="meter_data")
public class MeterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name="meter_id")
    private Meter meter;

    @Column(name="data")
    private int meterDataData;

    @Column(name="data_date")
    private String meterDataDate;

    public MeterData() {
    }

    public MeterData(int meterDataData, String meterDataDate) {
        this.meterDataData = meterDataData;
        this.meterDataDate = meterDataDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public int getMeterDataData() {
        return meterDataData;
    }

    public void setMeterDataData(int meterDataData) {
        this.meterDataData = meterDataData;
    }

    public String getMeterDataDate() {
        return meterDataDate;
    }

    public void setMeterDataDate(String meterDataDate) {
        this.meterDataDate = meterDataDate;
    }
}
