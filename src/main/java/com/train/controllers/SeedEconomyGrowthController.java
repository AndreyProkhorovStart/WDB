package com.train.controllers;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.EconomyGrowth;
import com.train.services.EconomyGrowthService;
import com.train.services.CountryService;
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
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@ApiIgnore
public class SeedEconomyGrowthController {

    @Autowired
    private EconomyGrowthService economyGrowthService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private IndicatorsService indicatorsService;

    @Autowired
    private GroupsService groupsService;

    private final String KEY_FOR_ECONOMY_GROWTH_INDICATORS= "economyGrowth";
    private final String START_URL = "http://api.worldbank.org/v2/country/all/indicator/";
    private final String END_URL = "?format=json";
    private final String[]
            INDICATORS =
            {       "NY.GDP.MKTP.KD.ZG", //Рост ВВП (годовой %)
                    "FP.CPI.TOTL.ZG" //Инфляция
            };

    private JSONArray getJSON(String url) throws org.jooq.tools.json.ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONParser parser = new JSONParser();

        return (JSONArray) parser.parse(response.getBody());
    }

    private List<String> getListUrl(String indicator) throws ParseException {
        List<String> urlPages = new ArrayList<>();
        JSONArray json = getJSON(START_URL + indicator + END_URL); // весь json
        JSONObject informationOnPages = (JSONObject) json.get(0); //информация о страницах
        Long countPages = (Long) informationOnPages.get("pages");
        for (int i = 1; i <= countPages; i++) {
            urlPages.add(START_URL + indicator + END_URL + "&page=" + i);
        }
        return urlPages;
    }

    private List<JSONObject> getAllRecords(List<String> urlPages) throws ParseException {
        List<JSONObject> countries = new ArrayList<>();
        for (String url : urlPages) {
            JSONArray tempJson = getJSON(url);
            JSONArray country = (JSONArray) tempJson.get(1);
            for (int i = 0; i < country.size(); i++) {
                countries.add((JSONObject) country.get(i));
            }
        }
        return countries;
    }

    private void seedIndicatorController() throws ParseException {
        for (int i = 0; i < INDICATORS.length; i++) {
            //создаю список ссылок
            List<String> urlPages = getListUrl(INDICATORS[i]);
            //создаю список всех записей со всех ссылок
            List<JSONObject> countries = getAllRecords(urlPages);
            //перебираю все страны
            int idIndicator = indicatorsService.getByCodeAndGroupId(INDICATORS[i], 3);
            groupsService.update(3);
            indicatorsService.update(idIndicator);
            for (JSONObject oneCountry : countries) {
                EconomyGrowth economyGrowth = new EconomyGrowth();
                String countryiso3code = String.valueOf(oneCountry.get("countryiso3code"));
                String value = String.valueOf(oneCountry.get("value"));
                if (!value.equals("null") || countryiso3code.equals("")) {
                    economyGrowth.setValue(value);
                } else {
                    continue;
                }
                String date = String.valueOf(oneCountry.get("date"));
                if (countryService.getByCodeName(countryiso3code) != null) {
                    int idCountry = countryService.getByCodeName(countryiso3code);
                    economyGrowth.setIdCountry(idCountry);
                    economyGrowth.setYear(date);
                    economyGrowth.setIdIndicator(idIndicator);
                    economyGrowthService.create(economyGrowth);
                }
            }
        }
        System.out.println("Загрузка успешна завершена!");
    }


    @GetMapping(value = "/getEconomyGrowthIndicator", consumes = "application/json")
    public void getEconomyGrowthIndicator(@RequestBody @Valid JSONObject inputKey) throws ParseException {
        if (inputKey.get("key").equals(KEY_FOR_ECONOMY_GROWTH_INDICATORS) && economyGrowthService.getAll().isEmpty()) {
            seedIndicatorController();
        } else {
            System.out.println("Загрузка не произошла");
        }
    }
}

