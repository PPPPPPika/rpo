package com.example.rpo.Controllers;

import com.example.rpo.Models.Comment;
import com.example.rpo.Services.InjectionService;
import com.example.rpo.Services.OperationService;
import com.example.rpo.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/rpo")
public class MainController {
    private final MainService mainService;
    private final OperationService operationService;
    private final InjectionService injectionService;

    @Autowired
    public MainController(@Qualifier("mainService") MainService mainService,
                          @Qualifier("operationService") OperationService operationService,
                          @Qualifier("injectionService") InjectionService injectionService) {
        this.mainService = mainService;
        this.operationService = operationService;
        this.injectionService = injectionService;
    }

    @GetMapping("/mainPage")
    public String getMainPage(@ModelAttribute("comment") Comment comment, Model model) {
        model.addAttribute("comments", mainService.getAllComments());
        return "mainPage";
    }

    @GetMapping("/comment/new")
    public String getPageNewComment(@ModelAttribute("comment") Comment comment){
        return "createComment";
    }

    @PostMapping("/comment")
    public String postNewComment(@ModelAttribute("comment") @Valid Comment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "createComment";
        operationService.addComment(comment, SecurityContextHolder.getContext().getAuthentication().getName()/*name of authenticated user*/);
        return "redirect:/rpo/mainPage";
    }

    @GetMapping("/comment/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id, @ModelAttribute("editComment") Comment comment, Model model){
        model.addAttribute("comment", injectionService.extractComment(id));
        return "editComment";
    }

    @PatchMapping("/comment/{id}")
    public String patchComment(@PathVariable("id") Long id,
                               @ModelAttribute("editComment") Comment comment){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        operationService.editComment(id, currentUser, comment.getText());
        return "redirect:/rpo/mainPage";
    }
}
