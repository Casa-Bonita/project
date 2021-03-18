package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="number")
    private int placeNumber;

    @Column(name="name")
    private String placeName;

    @Column(name="square")
    private double placeSquare;

    @Column(name="floor")
    private int placeFloor;

    @Column(name="type")
    private String placeType;

    @OneToOne(mappedBy="contractPlace", cascade = CascadeType.ALL)
    private Contract contract;

    @OneToOne(mappedBy="meterPlace", cascade = CascadeType.ALL)
    private Meter meter;

    public Place() {
    }

    public Place(int placeNumber, double placeSquare, int placeFloor, String placeType) {
        this.placeNumber = placeNumber;
        this.placeSquare = placeSquare;
        this.placeFloor = placeFloor;
        this.placeType = placeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getPlaceSquare() {
        return placeSquare;
    }

    public void setPlaceSquare(double placeSquare) {
        this.placeSquare = placeSquare;
    }

    public int getPlaceFloor() {
        return placeFloor;
    }

    public void setPlaceFloor(int placeFloor) {
        this.placeFloor = placeFloor;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }
}
