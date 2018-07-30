package ua.pp.sola.jsonvalidator.repo;

public interface VerySimpleRepo {
    public boolean isSupportedCustomer(String customer);
    public boolean isLegalEntity(String legalEntity);
    public boolean isValidISOCodes(String ccyPair);
}
