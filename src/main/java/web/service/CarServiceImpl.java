package web.service;


import org.springframework.stereotype.Service;
import web.model.Car;
import web.model.Color;

import java.util.ArrayList;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    private final List<Car> listCars;

    public CarServiceImpl() {
        listCars = new ArrayList<>();
        listCars.add(new Car("BMW", 6, Color.Black));
        listCars.add(new Car("VAZ", 2114, Color.Red));
        listCars.add(new Car("Mazda", 6, Color.White));
        listCars.add(new Car("Peugeot", 3008, Color.Blue));
        listCars.add(new Car("Peugeot", 308, Color.Yellow));
    }

    @Override
    public List<Car> getCars(int count) {
        if (count >= 5) {
            return listCars;
        }
        else if (count <= 0) {
            return new ArrayList<>();
        }
        return listCars.subList(0, count);
    }

}
