package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.Password;
import lv.kalashnikov.security_app.core.domain.Person;
import lv.kalashnikov.security_app.core.services.AddPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPersonController {

    @Autowired private AddPersonService service;

    @GetMapping(value = "/admin/addPerson")
    public String showAddPersonPage(ModelMap modelMap) {
        modelMap.addAttribute("person", new Person());
        modelMap.addAttribute("password", new Password());
        return "addPerson";
    }

    @PostMapping("/admin/addPerson")
    public String processAddPerson(@ModelAttribute(value = "person") Person person,
                                   @ModelAttribute(value = "password") Password password) {
        service.execute(person, password);
        return "redirect:/";
    }

}