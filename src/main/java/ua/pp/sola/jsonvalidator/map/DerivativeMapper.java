package ua.pp.sola.jsonvalidator.map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ua.pp.sola.jsonvalidator.entities.Derivative;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DerivativeMapper {

    private DerivativeFactory derivativeFactory;

    public List<Derivative> getDerivativeList(Derivative[] tradeInformation) throws IOException{
        List<Derivative> derivativesList = new LinkedList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Derivative item : tradeInformation){
            Map<String,Object> jsonMap = mapper.readValue(item, new TypeReference<Map<String,Object>>(){});
            derivativesList.add(derivativeFactory.createDrivative((String)jsonMap.get("type"),item));
        }
        return derivativesList;
    }

}
