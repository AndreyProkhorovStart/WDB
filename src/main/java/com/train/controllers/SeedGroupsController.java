package com.train.controllers;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Groups;
import com.train.services.GroupsService;
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
public class SeedGroupsController {
    @Autowired
    private GroupsService groupsService;

    private String urlGroups = "https://api.worldbank.org/v2/topic?format=json";
    private final String KEY_FOR_GROUPS = "groups";

    @GetMapping(value = "/getGroups", consumes = "application/json")
    public void getGroups(@RequestBody @Valid JSONObject inputKey) throws ParseException {
        if (inputKey.get("key").equals(KEY_FOR_GROUPS) && groupsService.getAll().isEmpty()) {
            seedGroupsController();
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

    private void seedGroupsController() throws ParseException {
        JSONArray json = getJSON(urlGroups);// весь json
        JSONObject informationOnPages  = (JSONObject) json.get(0); //информация о страницах
        Long countPages = (Long) informationOnPages.get("pages");
        for(int i = 1; i <= countPages; i++) {
            JSONArray tempJson = getJSON(urlGroups + "&page="+ i);
            JSONArray groups = (JSONArray) tempJson.get(1); //информация о странах с одной страницы
            JSONObject oneGroups; //одна страна
            System.out.print("страница номер: " + i + " ");
            for (int j = 0; j < groups.size(); j++) {
                oneGroups = (JSONObject) groups.get(j);
                Groups group = new Groups();
                group.setName((String) oneGroups.get("value"));
                int id = groupsService.create(group);
                System.out.println(id);
            }
        }
    }
}
