package org.mytelegram.bot.cityinfobot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    @PositiveOrZero
    private Long id;

    @NotBlank(message = "City name is mandatory")
    @Length(max = 500, message = "City name should less than 500 characters")
    private String name;

    @NotBlank(message = "City info is mandatory")
    @Length(max = 500, message = "City info should less than 500 characters")
    private String info;

    @Override
    public String toString() {
        return name + ": "
                + info;
    }

}
