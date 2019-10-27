package org.mytelegram.bot.cityinfobot.bot;

import org.mytelegram.bot.cityinfobot.model.City;
import org.mytelegram.bot.cityinfobot.model.dto.CityDto;
import org.mytelegram.bot.cityinfobot.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CityInfoBot extends TelegramLongPollingBot {

   private Logger logger = LoggerFactory.getLogger(CityInfoBot.class);

    private TelegramBotsApi telegramBotsApi;
    private CityService cityService;

    public CityInfoBot(TelegramBotsApi telegramBotsApi, CityService cityService) {
        this.telegramBotsApi = telegramBotsApi;
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String msg;
        String chatId;

        if (update.hasEditedMessage()) {
            msg = update.getEditedMessage().getText();
            chatId = update.getEditedMessage().getChatId().toString();
        } else {
            msg = update.getMessage().getText();
            chatId = update.getMessage().getChatId().toString();
        }

        switch (msg) {

            case "/start":
                sendMsg(chatId, "Привет. Воспользуйся списком городов");
                break;

            case "Города":
                cityService.findAll().stream()
                        .map(City::getCity)
                        .forEachOrdered(s -> sendMsg(chatId, s));
                break;

            case "Помощь":
                sendMsg(chatId, "Website: https://ru.wikipedia.org" +
                        "\nEmail: email@email.com");
                break;

            default:
                Optional<City> cityOptional = cityService.findByName(msg);
                if (cityOptional.isPresent()) {
                    City city = cityOptional.get();
                    CityDto cityDto = new CityDto();
                    cityDto.setName(city.getCity());
                    cityDto.setInfo(city.getInfo());
                    sendMsg(chatId, cityDto.toString());
                } else {
                    sendMsg(chatId, "Информация о таком городе отсутствует: " + msg);
                }
                break;
        }

    }

    @Override
    public String getBotUsername() {
        return "citytm_bot";
    }

    @Override
    public String getBotToken() {
        return "1026232361:AAHSnZ_iBqvS8oUvJL1RI4KxllaQtguSFVM";
    }

    private void setButtons(final SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Города"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Помощь"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    private void sendMsg(final String chatId, final String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Ошибка во время отправки сообщения ", e);
        }
    }


    @PostConstruct
    public void registryBot() {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            logger.error("Ошибка во время регистрации", e);
        }
    }
}
