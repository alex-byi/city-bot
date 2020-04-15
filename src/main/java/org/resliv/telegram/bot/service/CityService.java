package org.resliv.telegram.bot.service;

import org.resliv.telegram.bot.entity.City;

import java.util.List;

public interface CityService {

    City getCity(String cityName);

    List<City> getAllCities();

    City addCity(City city);

    City updateCity(City city);

    void deleteCity(int cityId);

}
