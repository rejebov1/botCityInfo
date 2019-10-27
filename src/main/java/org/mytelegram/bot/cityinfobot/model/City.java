package org.mytelegram.bot.cityinfobot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "City name is mandatory")
    @Length(max = 500, message = "City name should less than 256 characters")
    private String city;

    @NotBlank(message = "City info is mandatory")
    @Length(max = 500, message = "City info should less than 256 characters")
    private String info;

}
