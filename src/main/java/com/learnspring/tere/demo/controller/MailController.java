package com.learnspring.tere.demo.controller;

import com.learnspring.tere.demo.Utils;
import com.learnspring.tere.demo.dto.UserDTO;
import com.learnspring.tere.demo.model.Mail;
import com.learnspring.tere.demo.model.MailCategory;
import com.learnspring.tere.demo.model.User;
import com.learnspring.tere.demo.repository.MailCategoryRepository;
import com.learnspring.tere.demo.repository.MailRepository;
import com.learnspring.tere.demo.repository.UserRepository;
import jdk.jfr.Category;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/mails")
public class MailController {


    private final MailRepository mailRepository;
    private final MailCategoryRepository mailCategoryRepository;

    @Autowired
    public MailController(MailRepository mailRepository, MailCategoryRepository mailCategoryRepository) {
        this.mailRepository = mailRepository;
        this.mailCategoryRepository = mailCategoryRepository;
    }

    @GetMapping("/all")
    public List<Mail> getUsers() {
        return this.mailRepository.findAll();
    }


    @PostMapping("/import")
    public boolean importMails(@RequestParam("file") MultipartFile file) throws Exception {
        ArrayList<Mail> mails = new ArrayList<>();
        Utils.parseCSVFile(file.getInputStream()).forEach((CSVRecord record) -> {
            Mail mail = new Mail();
            mail.setContent(record.get("mail_content"));
            mail.setFrom(record.get("name"));
            mail.setSent_date(record.get("date"));
            mails.add(mail);
        });
        System.out.println("Finished parsing...");

        this.mailRepository.saveAll(mails);
        return true;
    }

    @PostMapping("/categorize")
    public boolean categorizeMails() {
        try {
            HashMap<String, MailCategory> categories = new HashMap<String, MailCategory>();
            this.mailCategoryRepository.findAll().forEach((MailCategory mailCat) -> {
                categories.put(mailCat.getName(), mailCat);
            });
            this.mailRepository.findAll().stream().filter((Mail mail) -> mail.getCateogry_id() == null).forEach((Mail mail) -> {
                String category = MailCategory.findCategory(mail.getContent());
                Optional<MailCategory> existing_cat = Optional.ofNullable(categories.get(category));
                if (existing_cat.isEmpty()) {
                    MailCategory newCat = new MailCategory();
                    newCat.setName(category);
                    MailCategory _newCat = this.mailCategoryRepository.save(newCat);
                    categories.put(_newCat.getName(), _newCat);
                    existing_cat = Optional.of(_newCat);
                }
                mail.setCateogry_id(existing_cat.orElseThrow().getId());
                this.mailRepository.save(mail);
            });
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
