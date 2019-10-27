package org.mytelegram.bot.cityinfobot.service.mapper;

import org.modelmapper.ModelMapper;
import org.mytelegram.bot.cityinfobot.model.City;
import org.mytelegram.bot.cityinfobot.model.dto.CityDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CityMapper {

    private ModelMapper mapper;

    public CityMapper(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public City toEntity(final CityDto cityDto) {
        return Objects.isNull(cityDto) ? null : mapper.map(cityDto, City.class);
    }

    public CityDto toDto(final City city) {
        return Objects.isNull(city) ? null : mapper.map(city, CityDto.class);
    }
}

