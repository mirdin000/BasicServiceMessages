package org.example.controllers;

import org.example.dao.MessageDao;
import org.example.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Validated
public class MassageControllers {
    private final MessageDao messageDao;
    @Autowired
    public MassageControllers(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/messages")
    public String messagesPage(@ModelAttribute("user") String user,
                               @ModelAttribute("message") Message message,
                               Model model){
        if (user == null || user.length() == 0){//проверка на null для переменной имени продавца
            return "redirect:/";
        }
        //message.setUsername(user);
        System.out.println("messages user: " + user);
        model.addAttribute("notes", messageDao.allNotes());
        message.setUsername(user);
        return "messages";
    }

    @GetMapping("/messages-filter")
    public String messagesPage2(@ModelAttribute("user") String user,
                                @ModelAttribute("username2") String username2,
                                @ModelAttribute("message") Message message,
                                Model model){
        System.out.println("messages-filter user: " + user);
        System.out.println("messages-filter username: " + username2);

        if (user == null || user.length() == 0){//проверка на null для переменной имени продоваца
            return "redirect:/";
        }
        if (username2 == null || username2.length() == 0){
            model.addAttribute("notes", messageDao.allNotes());
            return "messages";
        }
        model.addAttribute("notes", messageDao.notesByUsername(username2));
        return "messages";
    }

@PostMapping("/messages/new")
public String messagesNewPage(@ModelAttribute("user1") String user1,
                              @ModelAttribute("message") @Valid Message message,
                              BindingResult bindingResult,
                              Model model){
    if (user1 == null || user1.length() == 0){//проверка на null для переменной имени продавца
        return "redirect:/";
    }
    System.out.println("messages/new username: " + message.getUsername());
    System.out.println("messages/new message: " + message.getTextMessage());

    model.addAttribute("user", user1);

    //если будет обнаружена  ошибка в объекте при валидации поля текста, то вернётся представление с внедрённой ошибкой
    if (bindingResult.hasErrors()) {
        System.out.println("messages/new Error validation!!!");
        model.addAttribute("notes", messageDao.allNotes());
        return "messages";
    }

    messageDao.noteSave(message);
    System.out.println("messages/new Create message");
    model.addAttribute("notes", messageDao.allNotes());

    return "messages";
}

}