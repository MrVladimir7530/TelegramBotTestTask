package com.example.telegrambottesttask.service;

import com.example.telegrambottesttask.model.DailyDomains;
import com.example.telegrambottesttask.repository.DailyDomainsRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.List;


@Service
@PropertySource("bot.props")
public class DomainsService {
    private Logger log = LoggerFactory.getLogger(DomainsService.class);
    @Autowired
    DailyDomainsRepository dailyDomainsRepository;
    private Gson gson = new Gson();

    @Value("${url}")
    private String urlPath;

    @Scheduled(cron = "0 * * * * *")
    public void getDomains() {
        try {
            InputStream is = new URL(urlPath).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            TypeToken<List<DailyDomains>> listTypeToken = new TypeToken<>(){};
            List<DailyDomains> dailyDomains = gson.fromJson(jsonText, listTypeToken);
            dailyDomainsRepository.deleteAll();
            for (DailyDomains dailyDomain : dailyDomains) {
                dailyDomainsRepository.save(dailyDomain);
            }

        } catch (Exception e) {
           log.error(e.getMessage());
        }

    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
