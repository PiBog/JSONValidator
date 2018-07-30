package ua.pp.sola.jsonvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.sola.jsonvalidator.entities.Derivative;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ForwardService implements ValidationStrategy {

    public static final String SHORT_VALUE_DATE = "value date less then 3 days";

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
        if (!isForwardValid(derivative))
            report.put("error_5",SHORT_VALUE_DATE);
        if (report.isEmpty()) report.put("validationResult", "passed");

        return report;
    }

    public boolean isForwardValid(Derivative derivative){
        int valueDate = derivative.getValueDate().getDayOfYear();
        int tradeDate = derivative.getTradeDate().getDayOfYear();
        return (valueDate-tradeDate)>3;
    }
}
