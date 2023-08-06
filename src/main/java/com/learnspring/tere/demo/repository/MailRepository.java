package com.learnspring.tere.demo.repository;

import com.learnspring.tere.demo.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
    // You can add custom query methods here if needed
}
