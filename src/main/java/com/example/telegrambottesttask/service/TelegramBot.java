package com.example.telegrambottesttask.service;


import com.example.telegrambottesttask.config.BotConfig;
import com.example.telegrambottesttask.model.Users;
import com.example.telegrambottesttask.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private Logger log = LoggerFactory.getLogger(TelegramBot.class);
    @Autowired
    private UsersRepository usersRepository;

    private final BotConfig CONFIG;

    public TelegramBot(BotConfig CONFIG) {
        this.CONFIG = CONFIG;
    }

    @Override
    public String getBotUsername() {
        return CONFIG.getBotName();
    }

    @Override
    public String getBotToken() {
        return CONFIG.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage().getText().equals("/start")){
            registerUser(update.getMessage());
        }
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        prepareAndSendMessage(chatId, text);
    }


    private void prepareAndSendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("ERROR: " + e.getMessage());
        }
    }

    private void registerUser(Message message) {
        if (usersRepository.findById(message.getChatId()).isEmpty()) {
            Long chatId = message.getChatId();
            Chat chat = message.getChat();

            Users user = new Users();

            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUsername(chat.getUserName());
            user.setRegisteredAt(LocalDateTime.now());

            usersRepository.save(user);
            log.info("user saved: " + user);
        }
    }
}
