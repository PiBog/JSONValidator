package ua.pp.sola.jsonvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.sola.jsonvalidator.entities.Derivative;
import ua.pp.sola.jsonvalidator.entities.ForwardEntity;
import ua.pp.sola.jsonvalidator.entities.SpotEntity;
import ua.pp.sola.jsonvalidator.entities.VanillaOptionEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ValidationService {


    private ValidationStrategy validationStrategy;

    public void setValidationStrategy(Derivative derivative) {
        if (derivative instanceof ForwardEntity) this.validationStrategy = new ForwardService();
        if (derivative instanceof SpotEntity) this.validationStrategy = new SpotService();
        if (derivative instanceof VanillaOptionEntity) this.validationStrategy = new OptionService();
    }

    public List<Map<String, String>> getErrorsList(List<Derivative> tradeInformation) {

        List<Map<String, String>> report = new LinkedList<>();

        for (Derivative item : tradeInformation) {
            setValidationStrategy(item);
            report.add(validationStrategy.validate(item));
        }
        return report;
    }
}
