package org.resliv.telegram.bot.controller;


import org.resliv.telegram.bot.entity.City;
import org.resliv.telegram.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/cities/{city}")
    @ResponseStatus(HttpStatus.OK)
    public City getCity(@PathVariable String city){
       return cityService.getCity(city);
    }

    @PutMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public City updateCity(@RequestBody City city){
        return cityService.updateCity(city);
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody City city){
        return cityService.addCity(city);
    }

    @DeleteMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCity(@PathVariable int cityId){
        cityService.deleteCity(cityId);
    }

}
