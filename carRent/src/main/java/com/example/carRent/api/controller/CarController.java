package com.example.carRent.api.controller;

import java.util.List;
import java.util.Optional;

import com.example.carRent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.carRent.api.model.Car;
import com.example.carRent.api.model.DateRent;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> getAllCars() {
        return carService.getCarsList();
    }

    @GetMapping("/{plateNumber}")
    public ResponseEntity<Car> getCar(@PathVariable("plateNumber") String plateNumber) {
        Optional<Car> car = carService.getCar(plateNumber);
        if(car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{plateNumber}")
    public ResponseEntity<String> rentOrGetBackCar(@PathVariable("plateNumber") String plateNumber, @RequestParam(value="rent", required = true) boolean rent, @RequestBody(required = false) DateRent dates) {
        try {
            carService.updateRentStatus(plateNumber, rent, dates);
            return ResponseEntity.ok("Car OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
