package com.example.telegrambottesttask.service;


import com.example.telegrambottesttask.config.BotConfig;
import com.example.telegrambottesttask.model.Users;
import com.example.telegrambottesttask.model.UsersMessage;
import com.example.telegrambottesttask.repository.UsersMessageRepository;
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
    @Autowired
    private UsersMessageRepository usersMessageRepository;

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
        if (update.getMessage().getText().equals("/start")) {
            registerUser(update.getMessage());
        }
        Long chatId = update.getMessage().getChatId();
        String sentMessage = update.getMessage().getText();
        String receivedMessage = "You wrote: " + sentMessage;
        prepareAndSendMessage(chatId, receivedMessage);
        saveUsersMessage(chatId, sentMessage, receivedMessage);

        Users users = usersRepository.findById(chatId).get();
        users.setLastMessageAt(LocalDateTime.now());
        usersRepository.save(users);

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

            try {
                usersRepository.save(user);
                log.info("user saved: " + user);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }
    }

    public void saveUsersMessage(Long chatId, String sentMessage, String receivedMessage) {
        UsersMessage usersMessage = new UsersMessage();

        Users users = usersRepository.findById(chatId).get();

        usersMessage.setSentMessage(sentMessage);
        usersMessage.setReceivedMessage(receivedMessage);
        usersMessage.setUsers(users);

        try {
            usersMessageRepository.save(usersMessage);
            log.info("users message saved: " + usersMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
