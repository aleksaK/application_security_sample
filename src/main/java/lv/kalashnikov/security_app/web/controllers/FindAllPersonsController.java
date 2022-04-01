package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.Person;
import lv.kalashnikov.security_app.core.services.FindAllPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FindAllPersonsController {

    @Autowired private FindAllPersonsService service;

    @GetMapping(value = "/getAllPersons/{page}")
    public String showAllPersonsPage(@PathVariable String page, ModelMap modelMap) {
        Page<Person> persons = service.execute(page);
        modelMap.addAttribute("persons", persons);
        return "getAllPersons";
    }

}