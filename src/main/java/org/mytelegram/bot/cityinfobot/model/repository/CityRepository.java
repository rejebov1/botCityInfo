package org.mytelegram.bot.cityinfobot.model.repository;

import org.mytelegram.bot.cityinfobot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByCity(String cityName);

}
