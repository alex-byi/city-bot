package org.resliv.telegram.bot;

import lombok.extern.apachecommons.CommonsLog;
import org.resliv.telegram.bot.bot.CitiesBot;
import org.resliv.telegram.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@CommonsLog
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new CitiesBot(cityService));
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Application.class, args);
    }
}
