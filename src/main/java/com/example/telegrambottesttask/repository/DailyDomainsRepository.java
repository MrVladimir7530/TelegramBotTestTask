package com.example.telegrambottesttask.repository;

import com.example.telegrambottesttask.model.DailyDomains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyDomainsRepository extends JpaRepository<DailyDomains, Long> {
    @Query("SELECT count(*) FROM daily_domains")
    int getCountDomains();
}
