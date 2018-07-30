package ua.pp.sola.jsonvalidator.services;

import ua.pp.sola.jsonvalidator.entities.Derivative;

import java.util.Map;
import java.util.function.Predicate;

/**
 * will used pattern Strategy
 */
public interface ValidationStrategy {
    public Map<String,String> validate(Derivative derivative);
}
