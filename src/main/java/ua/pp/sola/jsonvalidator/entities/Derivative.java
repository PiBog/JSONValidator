package ua.pp.sola.jsonvalidator.entities;

import java.time.LocalDate;

public interface Derivative {
//    public String getType();
    public LocalDate getValueDate();
    public LocalDate getTradeDate();
    public String getCustomer();
    public String getCcyPair();
//    public String getLegalEntity();
}
