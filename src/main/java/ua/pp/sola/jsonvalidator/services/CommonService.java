package ua.pp.sola.jsonvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.sola.jsonvalidator.entities.Derivative;
import ua.pp.sola.jsonvalidator.repo.VerySimpleRepo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

@Service
public class CommonService {

    public static final String BEFORE_TRADE_DATE = "value date before trade date";
    public static final String NON_BUSINESSDAY = "value date fall on weekend or non-working day ";
    public static final String UNSUPPORTED_COUNTERPARTY = "unsupported counterparty";
    public static final String INVALID_CURR_ISO = "invalid ISO codes for currencies";

    @Autowired
    VerySimpleRepo samplesRepo;

    /**
     * Check value date, it cannot be before trade date
     * @param derivative that will checked
     * @return boolean - true if date valid
     */
    public boolean isValueDateValid(Derivative derivative){
        return derivative.getValueDate().isAfter(derivative.getTradeDate());
    }

    /**
     * Is Date A Business Day in USA?
     *
     * @param curDate
     * @return boolean
     */
    public boolean isBusinessDayUSA(LocalDate curDate) {

        // check if weekend
        if (curDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || curDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return false;
        }

        /**Fixed dates*/
        // check if New Year's Day
        if (curDate.getMonth().equals(Month.JANUARY) && curDate.getDayOfMonth() == 1) {
            return false;
        }
        // check if 4th of July
        if (curDate.getMonth().equals(Month.JULY) && curDate.getDayOfMonth() == 4) {
            return false;
        }

        // check if Christmas
        if (curDate.getMonth().equals(Month.DECEMBER) && curDate.getDayOfMonth() == 25) {
            return false;
        }

        /**Variable dates*/
        // check MLK Day (3rd Monday of January)
        if (curDate.isEqual(curDate.withMonth(1)
                .with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY)))) {
            return false;
        }

        // check President's Day (3rd Monday of February)
        if (curDate.isEqual(curDate.withMonth(2)
                .with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.MONDAY)))) {
            return false;
        }

        // check Memorial Day (last Monday of May)
        if (curDate.isEqual(curDate.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY)).withMonth(5))) {
            return false;
        }

        // check Labor Day (1st Monday of September)
        if (curDate.isEqual(curDate.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY)).withMonth(9))) {
            return false;
        }

        // check Thanksgiving (4th Thursday of November)
        if (curDate.isEqual(curDate.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY)).withMonth(11))) {
            return false;
        }

        // IF NOTHING ELSE, IT'S A BUSINESS DAY
        return true;
    }

    /**
     * Check if customer is supported
     * @param customer that will checked
     * @return boolean - true if supported
     */
    public boolean isCustomerSupported(String customer){
        return samplesRepo.isSupportedCustomer(customer);
    }

    /**
     * Check if ISO codes valid
     * @param currISOCode that will checked
     * @return boolean - true if valid
     */
    public boolean isCurrCodeValid(String currISOCode){
        return samplesRepo.isValidISOCodes(currISOCode);
    }

}
