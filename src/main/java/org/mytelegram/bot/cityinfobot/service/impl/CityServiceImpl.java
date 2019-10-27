package org.mytelegram.bot.cityinfobot.service.impl;

import org.mytelegram.bot.cityinfobot.model.City;
import org.mytelegram.bot.cityinfobot.model.repository.CityRepository;
import org.mytelegram.bot.cityinfobot.service.CityService;
import org.mytelegram.bot.cityinfobot.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public Optional<City> findByName(String cityName) {
        return cityRepository.findByCity(cityName);
    }


    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public City update(City updatedCity) throws CityNotFoundException {
        City city = cityRepository.findById(updatedCity.getId()).orElseThrow(CityNotFoundException::new);
        city.setCity(updatedCity.getCity());
        city.setCity(updatedCity.getCity());
        return city;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
