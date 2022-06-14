package com.train.controllers;

import com.train.services.IndicatorsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Контроллер Индикаторов
 * /indicators - Получить сведения о индикаторах
 * /indicators/{id} - Получить индикатор по id
 */
@RestController
public class IndicatorsController {
    @Autowired
    private IndicatorsService indicatorsService;

    /**
     * Получение конкретной записи
     *
     * @return
     */
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Получить сведения о индикаторах", notes = "Получить сведения о индикаторах")
    @GetMapping(value = "/indicators")
    public ResponseEntity<Object> index() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data", indicatorsService.getAll());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * Получение конкретной записи
     *
     * @param id
     * @return
     */
    //"http://localhost:3000"
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/indicators/{id}")
    @ApiOperation(value = "Получить индикатор по id", notes = "Получить индикатор по id")
    public ResponseEntity<Object> show(@PathVariable(value = "id", required = true) Integer id) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data", indicatorsService.read(id));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
