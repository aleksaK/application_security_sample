package lv.kalashnikov.security_app.web.controllers;

import lv.kalashnikov.security_app.core.domain.wrappers.Filter;
import lv.kalashnikov.security_app.core.domain.wrappers.Filters;
import lv.kalashnikov.security_app.core.services.FindPersonsBySpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.stream.IntStream;

@Controller
public class FindPersonsBySpecificationController {

    @Autowired private FindPersonsBySpecificationService service;

    @GetMapping(value = "/findPersonsBySpecification")
    public String showFindPersonsBySpecPage(ModelMap modelMap) {
        Filters filters = new Filters();
        IntStream.rangeClosed(0, 3).forEach(i -> filters.addFilter(new Filter()));
        modelMap.addAttribute("filters", filters);
        return "findPersonsBySpecification";
    }

    @PostMapping("/findPersonsBySpecification")
    public String processFindPersonsBySpec(@ModelAttribute(value = "filters") Filters filters, ModelMap modelMap) {

        if (filters.getFilters().stream()
                .anyMatch(filter ->
                        filter.getStringTarget() != null || filter.getIntegerTarget() != null)) {

            modelMap.addAttribute("filtered", service.execute(filters.getFilters()));
        }

        return "findPersonsBySpecification";
    }

}