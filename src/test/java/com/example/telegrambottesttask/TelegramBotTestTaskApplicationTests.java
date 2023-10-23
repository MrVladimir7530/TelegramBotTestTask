package com.example.telegrambottesttask;

import com.example.telegrambottesttask.config.GsonDateDeSerializer;
import com.example.telegrambottesttask.model.DailyDomains;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class TelegramBotTestTaskApplicationTests {
    private static GsonBuilder builder = new GsonBuilder();
    static{
        builder.registerTypeAdapter(Date.class, new GsonDateDeSerializer());
    }
    private static Gson gson = builder.create();


    private String user = " {\n" +
            "    \"domainname\": \"feokafa.ru\",\n" +
            "    \"hotness\": 0,\n" +
            "    \"price\": 150,\n" +
            "    \"x_value\": -1,\n" +
            "    \"yandex_tic\": 0,\n" +
            "    \"links\": 0,\n" +
            "    \"visitors\": -1,\n" +
            "    \"registrar\": \"BEGET-RU\",\n" +
            "    \"old\": 1,\n" +
            "    \"delete_date\": \"2023-11-17\",\n" +
            "    \"rkn\": false,\n" +
            "    \"judicial\": false,\n" +
            "    \"block\": false\n" +
            "  }";
    @Test
    void contextLoads() {
        DailyDomains dailyDomains = gson.fromJson(user, DailyDomains.class);
        System.out.println(dailyDomains);
    }


}
