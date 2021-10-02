package com.example.rpo.Controllers;

import com.example.rpo.Models.User;
import com.example.rpo.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/rpo")
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(@Qualifier("registrationService") RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user){
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistrationPage(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        boolean isDuplicate = registrationService.checkForDuplicate(user.getName());
        if (isDuplicate){
            model.addAttribute("isDuplicate", isDuplicate);
            return "registration";
        }
        registrationService.registrationUser(user);
        return "redirect:/rpo/login";
    }
}
