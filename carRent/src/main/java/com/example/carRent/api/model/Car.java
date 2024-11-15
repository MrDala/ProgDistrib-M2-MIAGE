package com.example.carRent.api.model;

import java.util.List;

public class Car {

    private String plateNumber;
    private String brand;
    private float price;
    private Boolean rent;
    private List<DateRent> dateRentsList;

    public Car(String plateNumber, String brand, float price, List<DateRent> dateRentsList) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.rent = false;
        this.dateRentsList = dateRentsList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Boolean getRent() {
        return rent;
    }

    public void setRent(Boolean rent) {
        this.rent = rent;
    }

    public List<DateRent> getDateRentsList() {
        return dateRentsList;
    }

    public void setDateRentsList(List<DateRent> dateRentsList) {
        this.dateRentsList = dateRentsList;
    }

    public void addDateRent(DateRent dateRent) {
        this.dateRentsList.add(dateRent);
    }
}
