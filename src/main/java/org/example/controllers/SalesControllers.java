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
public class SalesControllers {
    private final MessageDao messageDao;
    @Autowired
    public SalesControllers(MessageDao messageDao) {
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
        if (user == null){//проверка на null для переменной имени продоваца
            return "redirect:/";
        }
        System.out.println("user1: " + user);
        model.addAttribute("notes", messageDao.allNotes());
        return "messages";
    }

    @GetMapping("/messages-filter")
    public String messagesPage2(@ModelAttribute("user") String user,
                            @ModelAttribute("username") String username,
                            @ModelAttribute("message") Message message,
                            Model model){
        if (user == null){//проверка на null для переменной имени продоваца
            return "redirect:/";
        }
        System.out.println("user2: " + user);
        model.addAttribute("notes", messageDao.notesByUsername(username));
        return "messages";
    }

@PostMapping("/messages/new")
public String messagesNewPage(@Valid @ModelAttribute("message") Message message,
                              @ModelAttribute("user") String user,
                           BindingResult bindingResult, Model model){
    if (user== null){//проверка на null для переменной имени продоваца
        return "redirect:/";
    }
    if (bindingResult.hasErrors()) {
        System.out.println("Error validation!!!");
        return "redirect:/messages";
    }
    System.out.println("user3: " + user);

    message.setUsername(user);
    messageDao.noteSave(message);
    model.addAttribute("notes", messageDao.allNotes());
    return "messages";
}

}