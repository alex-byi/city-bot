package org.resliv.telegram.bot.service.impl;

import lombok.extern.apachecommons.CommonsLog;
import org.resliv.telegram.bot.entity.City;
import org.resliv.telegram.bot.exception.DuplicateCityException;
import org.resliv.telegram.bot.repository.CityRepository;
import org.resliv.telegram.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
@CommonsLog
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City getCity(String cityName) {
        return cityRepository.findByCity(cityName.toLowerCase())
                .orElse(City.builder()
                        .message("Вы ошиблись при вводе названия города или такого города еще нет в базе =(")
                        .build());
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City addCity(City city) {
        cityRepository.findByCity(city.getCity().toLowerCase()).ifPresent(city1 -> {
            log.error("duplicate city");
            throw new DuplicateCityException("duplicate city");
        });
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public City updateCity(City city) {
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public void deleteCity(int cityId) {
        cityRepository.deleteById(cityId);
    }
}
