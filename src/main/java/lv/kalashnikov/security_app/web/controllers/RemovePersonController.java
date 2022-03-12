package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.Nickname;
import lv.kalashnikov.security_app.core.services.RemovePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemovePersonController {

    @Autowired private RemovePersonService service;

    @GetMapping(value = "/admin/removePerson")
    public String showRemovePersonPage(ModelMap modelMap) {
        modelMap.addAttribute("nickname", new Nickname());
        return "removePerson";
    }

    @PostMapping("/admin/removePerson")
    public String processAddPerson(@ModelAttribute(value = "nickname") Nickname nickname,
                                   BindingResult result, ModelMap modelMap) {
        try {
            service.execute(nickname.getNickname());
            return "redirect:/";
        }
        catch (Exception e) {
            result.addError(new FieldError(
                    "","nickname","no user with such nickname!"));
            modelMap.addAttribute("error", result.toString());
        }
        return "removePerson";
    }

}