package com.train.controllers;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Country;
import com.train.services.CountryService;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.jooq.tools.json.ParseException;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@ApiIgnore
public class SeedCountryController {
    @Autowired
    private CountryService countryService;

    private String urlCountry = "https://api.worldbank.org/v2/country?format=json";
    private final String KEY_FOR_COUNTRY = "country";

    @GetMapping(value = "/getCountry", consumes = "application/json")
    public void getCountry(@RequestBody @Valid JSONObject inputKey) throws ParseException {
        if(inputKey.get("key").equals(KEY_FOR_COUNTRY) && countryService.getAll().isEmpty()){
            seedCountryRun();
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

    private void seedCountryRun() throws ParseException {
        JSONArray json = getJSON(urlCountry);// весь json
        JSONObject informationOnPages  = (JSONObject) json.get(0); //информация о страницах
        Long countPages = (Long) informationOnPages.get("pages");
        for(int i = 1; i <= countPages; i++) {
            JSONArray tempJson = getJSON(urlCountry + "&page="+ i);
            JSONArray country = (JSONArray) tempJson.get(1); //информация о странах с одной страницы
            JSONObject oneCountry; //одна страна
            System.out.print("страница номер: " + i + " ");
            for (int j = 0; j < country.size(); j++) {
                oneCountry = (JSONObject) country.get(j);
                Country country1 = new Country();
                country1.setCodeName((String) oneCountry.get("id"));
                country1.setFullName((String) oneCountry.get("name"));
                int id = countryService.create(country1);
                System.out.println(id);
            }
        }
    }

}