package org.resliv.telegram.bot.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.resliv.telegram.bot.entity.City;
import org.resliv.telegram.bot.repository.CityRepository;
import org.resliv.telegram.bot.service.CityService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService = new CityServiceImpl();

    @BeforeEach
    void setMockOutput() {
        lenient().when(cityRepository
                .findByCity(Mockito.anyString()))
                .thenReturn(Optional.of(City.builder()
                        .city("testCity")
                        .message("test message").build()));

        lenient().when(cityRepository.findAll()).thenReturn(Arrays.asList(
                City.builder()
                        .id(1)
                        .city("first")
                        .message("firstMessage").build(),
                City.builder()
                        .id(1)
                        .city("second")
                        .message("secondMessage").build()));
    }

    @DisplayName("Get city by city name test")
    @Test
    public void getCity() {

        assertEquals("testCity", cityRepository.findByCity("test")
                .orElse(City.builder()
                        .message("error")
                        .build()).getCity());

        assertEquals("test message", cityRepository.findByCity("test")
                .orElse(City.builder()
                        .message("error")
                        .build()).getMessage());
    }

    @DisplayName("Get all cities test")
    @Test
    void getAllCities() {

        assertEquals(2, cityRepository.findAll().size());

        assertEquals("first", cityRepository.findAll().get(0).getCity());
        assertEquals("firstMessage", cityRepository.findAll().get(0).getMessage());

        assertEquals("second", cityRepository.findAll().get(1).getCity());
        assertEquals("secondMessage", cityRepository.findAll().get(1).getMessage());
    }
}