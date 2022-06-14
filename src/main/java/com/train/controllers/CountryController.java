package com.train.controllers;

import com.train.services.CountryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Контроллер Стран
 * /country - Получить сведения о странах
 * /country/{id} - Получить страну по id
 */

@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;
    /**
     * Получение всех записей
     *
     * @return
     */
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Получить сведения о странах", notes = "Получить сведения о странах")
    @GetMapping(value = "/country")
    public ResponseEntity<Object> index() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data", countryService.getAll());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * Получение конкретной записи
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/country/{id}")
    @ApiOperation(value = "Получить страну по id", notes = "Получить страну по id")
    public ResponseEntity<Object> show(@PathVariable(value = "id", required = true) Integer id) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data", countryService.read(id));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
