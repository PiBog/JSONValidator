package ua.pp.sola.jsonvalidator.repo;


import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Repository
public class SamplesRepository implements VerySimpleRepo{

    private Set<String> supportedCustomers = new HashSet<>(Arrays.asList(new String[]{"PLUTO1", "PLUTO2"}));
    private Set<String> legalEntities = new HashSet<>(Arrays.asList(new String[]{"CS Zurich"}));
    private Set<String> validISOCodes = new HashSet<>(Arrays.asList(new String[]{"EURUSD"}));

    @Override
    public boolean isSupportedCustomer(String customer) {
        if (supportedCustomers.contains(customer))return true;
        return false;
    }

    @Override
    public boolean isLegalEntity(String legalEntity) {
        if (legalEntities.contains(legalEntity))return true;
        return false;
    }

    @Override
    public boolean isValidISOCodes(String ccyPair) {
        if (validISOCodes.contains(ccyPair))return true;
        return false;
    }
}
