package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.Person;
import lv.kalashnikov.security_app.core.services.FindBySpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetAllPersonsBySpecsController {

    @Autowired
    private FindBySpecificationService service;

    @GetMapping(value = "/getAllPersonsBySpecs")
    public String showAllPersonsBySpecsPage(ModelMap modelMap) {
        List<Person> persons = service.execute();
        modelMap.addAttribute("persons", persons);
        return "findBySpecs";
    }

}