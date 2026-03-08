package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Car;
import web.Service.CarServiceImpl;

import java.util.List;

@Controller
public class CarController {
    private final CarServiceImpl carService;
    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String showCars(ModelMap model,
                           @RequestParam(value = "count", defaultValue = "5") int count) {
        List<Car> cars = carService.getCars(count);
        model.addAttribute("requestcars", cars);
        return "cars";
    }
}
