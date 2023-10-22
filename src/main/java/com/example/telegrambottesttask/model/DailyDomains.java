package com.example.telegrambottesttask.model;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "daily_domains")
public class DailyDomains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyDomainsId;
    private String domainname;
    private int hotness;
    private int price;
    private int xValue;
    private int yandexTic;
    private int links;
    private int visitors;
    private String registrar;
    private int old;
    @Column(name = "delete_date")
    private LocalDate deleteDate;
    private boolean rkn;
    private boolean judicial;
    private boolean block;

}
