package ua.pp.sola.jsonvalidator.entities;

public class DerivativeFactory {
    public Derivative createDrivative(final String type) {
        if (type.equalsIgnoreCase("Spot")) {
            return new SpotEntity();
        } else if (type.equalsIgnoreCase("Forward")) {
            return new ForwardEntity();
        } else if (type.equalsIgnoreCase("VanillaOption")) {
            return new VanillaOptionEntity();
        } else {throw new IllegalArgumentException("Unknown type of derivative");}
    }
}
