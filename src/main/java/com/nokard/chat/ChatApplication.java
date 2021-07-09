package com.nokard.chat;

import com.nokard.chat.entity.ChatMember;
import com.nokard.chat.entity.User;
import com.nokard.chat.repository.UsersRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;


@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {


        SpringApplication.run(ChatApplication.class, args);
    }

}
