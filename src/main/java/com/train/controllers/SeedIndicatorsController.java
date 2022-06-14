package com.train.controllers;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Indicators;
import com.train.services.GroupsService;
import com.train.services.IndicatorsService;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@ApiIgnore
public class SeedIndicatorsController {
    @Autowired
    private IndicatorsService indicatorsService;

    @Autowired
    private GroupsService groupsService;

    private final String START_URL = "https://api.worldbank.org/v2/topic/";
    private final String END_URL = "/indicator?format=json";
    private final String KEY_FOR_INDICATORS= "indicators";

    @GetMapping(value = "/getIndicators", consumes = "application/json")
    public void getIndicators(@RequestBody @Valid JSONObject inputKey) throws ParseException {
        System.out.println("key");
        if (inputKey.get("key").equals(KEY_FOR_INDICATORS) && indicatorsService.getAll().isEmpty()) {
            seedIndicatorsController();
        } else {
            System.out.println("Загрузка не произошла");
        }
    }

    private JSONArray getJSON(String url) throws org.jooq.tools.json.ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(response.getBody()); // весь json
        return json;
    }

    private void seedIndicatorsController() throws ParseException {
        for (int k = 1; k <= 21; k++) {
            JSONArray json = getJSON(START_URL + k + END_URL);// весь json
            JSONObject informationOnPages = (JSONObject) json.get(0); //информация о страницах
            Long countPages = (Long) informationOnPages.get("pages");
            for (int i = 1; i <= countPages; i++) {
                JSONArray tempJson = getJSON(START_URL + k + END_URL + "&page=" + i);
                JSONArray indicators = (JSONArray) tempJson.get(1);
                JSONObject oneIndicators;
                System.out.print("страница номер: " + i + " ");
                for (int j = 0; j < indicators.size(); j++) {
                    oneIndicators = (JSONObject) indicators.get(j);
                    Indicators indicator = new Indicators();
                    indicator.setIdGroup(k);
                    indicator.setCode((String) oneIndicators.get("id"));
                    indicator.setName((String) oneIndicators.get("name"));
                    int id = indicatorsService.create(indicator);
                    System.out.println(id);
                }
            }
        }
    }
}
