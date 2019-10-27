package org.mytelegram.bot.cityinfobot.service;

import org.mytelegram.bot.cityinfobot.model.City;
import org.mytelegram.bot.cityinfobot.service.exception.CityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CityService {

    Optional<City> findById(Long id);

    Optional<City> findByName(String cityName);

    City save(City city);

    void delete(City city);

    City update(City city) throws CityNotFoundException;

    List<City> findAll();

}
