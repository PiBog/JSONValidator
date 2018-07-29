package ua.pp.sola.jsonvalidator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.sola.jsonvalidator.entities.Derivative;
import ua.pp.sola.jsonvalidator.services.ValidationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    @Autowired
    ValidationService validationService;

    @RequestMapping(value = "/json", method = {RequestMethod.POST}, produces = "application/json")
    public List<String> validate(@RequestBody List<Derivative> tradeInformation) {
        LOGGER.info("get:"+tradeInformation.toString() );
        return null;

    }


}
