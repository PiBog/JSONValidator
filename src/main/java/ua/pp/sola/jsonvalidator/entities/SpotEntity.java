package ua.pp.sola.jsonvalidator.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SpotEntity implements Derivative {

    private final String type;
    private String customer;
    private String ccyPair;
    private String direction;
    private LocalDate tradeDate;
    private float amount1;
    private float amount2;
    private float rate;
    private LocalDate valueDate;
    private String legalEntity;
    private String trader;

    public SpotEntity(){
        this.type = "Spot";
    }
}
