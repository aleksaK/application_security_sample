package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.Person;
import lv.kalashnikov.security_app.core.services.GetAllPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class GetAllPersonsController {

    @Autowired private GetAllPersonsService service;

    @GetMapping(value = "/getAllPersons")
    public String showAllPersonsPage(ModelMap modelMap) {
        List<Person> persons = service.execute();
        modelMap.addAttribute("persons", persons);
        return "getAllPersons";
    }

}