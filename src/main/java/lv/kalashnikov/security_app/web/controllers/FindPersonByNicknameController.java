package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.wrappers.Nickname;
import lv.kalashnikov.security_app.core.domain.Person;
import lv.kalashnikov.security_app.core.services.FindPersonByNicknameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class FindPersonByNicknameController {

    @Autowired private FindPersonByNicknameService service;

    @GetMapping(value = "/getPersonByNickname")
    public String showFindPersonPage(ModelMap modelMap) {
        modelMap.addAttribute("nickname", new Nickname());
        return "getPersonByNickname";
    }

    @PostMapping("/getPersonByNickname")
    public String processFindPerson(@ModelAttribute(value = "nickname") Nickname nickname, ModelMap modelMap) {
        Optional<Person> person = service.execute(nickname.getNickname());
        if (person.isPresent()) {
            modelMap.addAttribute("person", person.get());
        }
        else {
            modelMap.addAttribute("noResult", "No such person in database!");
        }
        return "getPersonByNickname";
    }

}