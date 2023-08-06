package com.learnspring.tere.demo.repository;

import com.learnspring.tere.demo.model.Mail;
import com.learnspring.tere.demo.model.MailCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailCategoryRepository extends JpaRepository<MailCategory, Long> {
    // You can add custom query methods here if needed

}
