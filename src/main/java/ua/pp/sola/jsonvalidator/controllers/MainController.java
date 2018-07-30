package ua.pp.sola.jsonvalidator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.pp.sola.jsonvalidator.entities.Derivative;
import ua.pp.sola.jsonvalidator.map.DerivativeMapper;
import ua.pp.sola.jsonvalidator.services.ValidationService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/validation")
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    @Autowired
    ValidationService validationService;

    @Autowired
    DerivativeMapper derivativeMapper;

    @RequestMapping(value = "/json", method = {RequestMethod.POST}, produces = "application/json")
    public List<Map<String,String>> validate(@RequestBody Derivative[] tradeInformation) {
        List<Derivative> entityList = null;
        try {
            LOGGER.info("get:"+tradeInformation.toString() );
            entityList = derivativeMapper.getDerivativeList(tradeInformation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validationService.getErrorsList(entityList);
    }


}
