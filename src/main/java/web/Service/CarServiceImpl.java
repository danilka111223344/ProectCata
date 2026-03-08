package web.Service;


import org.springframework.stereotype.Service;
import web.Model.Car;

import java.util.ArrayList;

import java.util.List;


@Service
public class CarServiceImpl {
    private final List<Car> listCars;

    public CarServiceImpl() {
        listCars = new ArrayList<>();
        listCars.add(new Car("BMW", 6, "black"));
        listCars.add(new Car("VAZ", 2114, "red"));
        listCars.add(new Car("Mazda", 6, "white"));
        listCars.add(new Car("Peugeot", 3008, "brown"));
        listCars.add(new Car("Peugeot", 308, "blue"));
    }

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
