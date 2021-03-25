package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;
import java.util.Date;

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
    private int transferData;

    @Column(name="data_date")
    private Date transferDate;

    public MeterData() {
    }

    public MeterData(Meter meter, int transferData, Date transferDate) {
        this.meter = meter;
        this.transferData = transferData;
        this.transferDate = transferDate;
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

    public int getTransferData() {
        return transferData;
    }

    public void setTransferData(int transferData) {
        this.transferData = transferData;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
}
