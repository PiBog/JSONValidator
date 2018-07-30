package ua.pp.sola.jsonvalidator.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter()
@Setter
public class VanillaOptionEntity implements Derivative {
    private final String type;
    private String customer;
    private String ccyPair;
    private String style;
    private String direction;
    private String strategy;
    private LocalDate tradeDate;
    private float amount1;
    private float amount2;
    private float rate;
    private LocalDate deliveryDate;
    private LocalDate valueDate;
    private LocalDate excerciseStartDate;
    private String payCcy;
    private float premium;
    private String premiumCcy;
    private String premiumType;
    private LocalDate premiumDate;
    private String legalEntity;
    private String trader;

    public VanillaOptionEntity(){
        this.type = "VanillaOption";
    }

    @JsonSetter("expiryDate")
    public void setValueDate(final LocalDate expiryDate) {
        this.valueDate = expiryDate;
    }

    @JsonGetter("expiryDate")
    public LocalDate getValueDate() {
        return valueDate;
    }
}
