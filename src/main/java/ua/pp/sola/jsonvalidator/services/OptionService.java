package ua.pp.sola.jsonvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.sola.jsonvalidator.entities.Derivative;
import ua.pp.sola.jsonvalidator.entities.VanillaOptionEntity;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OptionService implements ValidationStrategy {

    public static final String WRONG_DELIVERY_DATE = "invalid deliveri date";
    public static final String WRONG_USA_STYLE = "invalid excerciseStartDate";

    @Autowired
    CommonService commonService;

    @Override
    public Map<String, String> validate(Derivative derivative) {
        Map<String, String> report = new LinkedHashMap<>();
        if (!commonService.isValueDateValid(derivative))
            report.put("error_1", commonService.BEFORE_TRADE_DATE);
        if (!commonService.isBusinessDayUSA(derivative.getValueDate()))
            report.put("error_2", commonService.NON_BUSINESSDAY);
        if (!commonService.isCustomerSupported(derivative.getCustomer()))
            report.put("error_3", commonService.UNSUPPORTED_COUNTERPARTY);
        if (!commonService.isCurrCodeValid(derivative.getCcyPair()))
            report.put("error_4", commonService.INVALID_CURR_ISO);
        if (!commonService.isCurrCodeValid(derivative.getCcyPair()))
            report.put("error_4", commonService.INVALID_CURR_ISO);
        if (!isOptionValid(derivative))
            report.put("error_5",WRONG_DELIVERY_DATE);
        if (!isOptionValid(derivative))
            report.put("error_6",WRONG_USA_STYLE);
        if (report.isEmpty()) report.put("validationResult", "passed");

        return report;
    }

    public boolean isOptionValid(Derivative derivative){
        VanillaOptionEntity optionEntity = (VanillaOptionEntity) derivative;
        LocalDate expiryDate = optionEntity.getValueDate();
        LocalDate premiumDate = optionEntity.getTradeDate();
        LocalDate deliveryDate = optionEntity.getDeliveryDate();

        return expiryDate.isBefore(deliveryDate)&&premiumDate.isBefore(deliveryDate);
    }
    public boolean isUSAOptionValid(Derivative derivative){
        VanillaOptionEntity optionEntity = (VanillaOptionEntity) derivative;
        LocalDate expiryDate = optionEntity.getValueDate();
        LocalDate tradeDate = optionEntity.getTradeDate();
        LocalDate excerciseStartDate = optionEntity.getExcerciseStartDate();

        return excerciseStartDate.isAfter(tradeDate)&&excerciseStartDate.isBefore(expiryDate);
    }

}
