package com.train.controllers;

import com.train.dto.ClimateChangeDto;
import com.train.services.ClimateChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Контроллер ClimateChange
 */
@RestController
public class ClimateChangeController {

    @Autowired
    private ClimateChangeService climateChangeService;


    @ApiOperation(value = "Получить сведения о группе индикаторов ClimateChange", notes = "Получить сведения о группе индикаторов ClimateChange")
    @PostMapping(value = "/ClimateChange", consumes = "application/json")
    public ResponseEntity<Object> getClimateChangeIndicator(@RequestBody @Valid ClimateChangeDto request) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data" , climateChangeService.getClimateChangeIndicator(request));
        return new ResponseEntity<Object>(body, httpHeaders, HttpStatus.OK);
    }
}
