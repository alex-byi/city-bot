package org.resliv.telegram.bot.bot;

import lombok.extern.apachecommons.CommonsLog;
import org.resliv.telegram.bot.service.CityService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@CommonsLog
public class CitiesBot extends TelegramLongPollingBot {

    private CityService cityService;

    public CitiesBot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        sendMessage(update);
    }

    @Override
    public String getBotUsername() {
        return "cityExampleBot";
    }

    @Override
    public String getBotToken() {
        return "1107383880:AAHYOovZoeEW6DNNRBRudRbzuncUdVR5SnQ";
    }

    private void sendMessage(Update update) {
        String message = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();

        if (message.contains("/start")){
            sendMessage.setChatId(update.getMessage().getChatId())
                    .setText("Hello, I am city Bot. " +
                            "Send me the name of the city in Russian and I will give you information about it");
        } else {
            sendMessage.setChatId(update.getMessage().getChatId())
                    .setText(cityService.getCity(message).getMessage());
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}