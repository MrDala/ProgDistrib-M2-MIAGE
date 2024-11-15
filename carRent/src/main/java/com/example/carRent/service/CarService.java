package com.example.carRent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.carRent.api.model.Car;
import com.example.carRent.api.model.DateRent;

@Service
public class CarService {

    private final List<Car> carsList;

    public CarService() {
        List<DateRent> dateRents1 = new ArrayList<>();
        dateRents1.add(new DateRent("01/01/2024", "30/01/2024"));
        dateRents1.add(new DateRent("02/03/2024", "15/03/2024"));

        List<DateRent> dateRents2 = new ArrayList<>();
        dateRents2.add(new DateRent("15/03/2023", "15/04/2023"));

        List<DateRent> dateRents3 = new ArrayList<>();
        dateRents3.add(new DateRent("12/12/2012", "31/12/2012"));

        carsList = new ArrayList<>();
        carsList.add(new Car("ABC123", "Toyota", 15000.50f, dateRents1));
        carsList.add(new Car("XYZ789", "Honda", 12000.75f, dateRents2));
        carsList.add(new Car("CCBWK2", "Ferrari", 32000.75f, dateRents3));
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public Optional<Car> getCar(String plateNumber) {
        Optional<Car> optional = Optional.empty();
        for (Car car : carsList) {
            if (plateNumber.equals(car.getPlateNumber())) {
                return Optional.of(car);
            }
        }
        return optional;
    }

    public void updateRentStatus(String plateNumber, boolean rent, DateRent dates) throws Exception {
        Optional<Car> optional = getCar(plateNumber);

        if (!optional.isPresent()) {
            throw new Exception("The car with the number plate " + plateNumber + " cannot be found.");
        }

        Car car = optional.get();

        if (car.getRent() == rent) {
            throw new Exception("The car is " + (rent ? "already rented." : "not rented."));
        }

        if (rent) {
            validateDateRent(dates);
            car.setRent(true);
            car.addDateRent(new DateRent(dates.getBegin(), dates.getEnd()));
        } else {
            car.setRent(false);
        }
    }

    private void validateDateRent(DateRent dates) {
        if (dates == null || dates.getBegin() == null || dates.getEnd() == null) {
            throw new IllegalArgumentException("Dates must be provided when renting the car.");
        }
    }
}
