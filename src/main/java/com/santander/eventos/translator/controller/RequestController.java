package com.santander.eventos.translator.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.santander.eventos.translator.Transactions.Mp7e;

@RestController
public class RequestController {

    @PostMapping("/event-transalate")
    public String translateEvent(@RequestBody String eventString) {
                
try{        
    eventString.replace("eventMetadata","eventHeader");
        
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode json = objectMapper.createObjectNode();
            Map<String, Object> jsonMap = objectMapper.readValue(eventString,Map.class);
            JsonNode rootNode = objectMapper.readTree(eventString);
            ObjectNode objectNode = (ObjectNode) rootNode;
            JsonNode evmetdtNode = rootNode.get("eventMetadata");
            objectNode.put("eventHeader", rootNode.get("eventMetadata"));
            objectNode.remove("eventMetadata");
            String reftrans = evmetdtNode.get("refTrans").asText();
            System.out.println(reftrans);
            if(reftrans.equals("MP7E")){
                jsonMap.forEach((key,value)) ->{
                    json.put(Mp7e.valueOf(key).getValue(),value.toString());
                });
                System.out.println(json.toPrettyString());
            }else if(reftrans.equals("SIADB")){
                jsonMap.forEach((key,value)) ->{
                    json.put(Siadb.valueOf(key).getValue(),value.toString());
                });
                System.out.println(json.toPrettyString());
            }else{
                System.out.println("error");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
return eventString.toString();
    }
}