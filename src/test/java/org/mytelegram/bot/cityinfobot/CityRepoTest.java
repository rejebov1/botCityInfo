package org.mytelegram.bot.cityinfobot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mytelegram.bot.cityinfobot.model.City;
import org.mytelegram.bot.cityinfobot.model.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CityRepoTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void addCitiesThenFindByName(){
        City minsk = City.builder()
                .city("Минск")
                .info("Cтолица Беларуси. Минск расположен на юго-восточном склоне Минской возвышенности.Площадь 348,84 км².Население 1 992 685 человек(2019)")
                .build();
        City moscow = City.builder()
                .city("Москва")
                .info("Столица России. Расположена на западе России, на реке Москве в центре Восточно-Европейской равнины. Площадь 2561,5 км². Население 12 615 279 человек(2019)")
                .build();
        City kyiv = City.builder()
                .city("Киев")
                .info("Столица Украины. Расположен на реке Днепр. Площадь 847,66 км². Население 2 950 906 человека(2019)")
                .build();
        City vilnius = City.builder()
                .city("Вильнюс")
                .info("Столица Литвы. Расположен на крайнем юго-востоке Литвы. Площадь 401 км². Население 574 147 человека(2018)")
                .build();
        City warsaw = City.builder()
                .city("Варшава")
                .info("Столица и крупнейший по населению и по территории город в Польше. Площадь 517 км². Население 1 758 143 человек(2017)")
                .build();

        cityRepository.save(minsk);
        cityRepository.save(moscow);
        cityRepository.save(kyiv);
        cityRepository.save(vilnius);
        cityRepository.save(warsaw);

        Optional<City> result = cityRepository.findByCity("Москва");
        assertTrue(result.isPresent());

    }
}
