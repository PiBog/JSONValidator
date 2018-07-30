package ua.pp.sola.jsonvalidator.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.pp.sola.jsonvalidator.entities.Derivative;
import ua.pp.sola.jsonvalidator.entities.ForwardEntity;
import ua.pp.sola.jsonvalidator.entities.SpotEntity;
import ua.pp.sola.jsonvalidator.entities.VanillaOptionEntity;

import java.io.IOException;

/**
 * use pattern Factory
 */
public class DerivativeFactory {

    ObjectMapper mapper = new ObjectMapper();

    public Derivative createDrivative(final String type, final String item) throws IllegalArgumentException, IOException {
        if (type.equalsIgnoreCase("Spot")) {
            return mapper.readValue(item, SpotEntity.class);
        } else if (type.equalsIgnoreCase("Forward")) {
            return mapper.readValue(item, ForwardEntity.class);
        } else if (type.equalsIgnoreCase("VanillaOption")) {
            return mapper.readValue(item, VanillaOptionEntity.class);
        } else {throw new IllegalArgumentException("Unknown type of derivative");}
    }
}
