package com.train.controllers;

import com.train.dto.EconomyGrowthDto;
import com.train.services.EconomyGrowthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Контроллер EconomyGrowth
 */
@RestController
public class EconomyGrowthController {

    @Autowired
    private EconomyGrowthService economyGrowthService;
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Получить сведения о группе индикаторов EconomyGrowth", notes = "Получить сведения о группе индикаторов EconomyGrowth")
    @PostMapping(value = "/Economy&Growth", consumes = "application/json")
    public ResponseEntity<Object> getEconomyGrowthIndicator(@RequestBody @Valid EconomyGrowthDto request) {
        Map<String, Object> body = new LinkedHashMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        body.put("timestamp", LocalDateTime.now());
        body.put("data" , economyGrowthService.getEconomyGrowthIndicator(request));
        return new ResponseEntity<Object>(body, httpHeaders, HttpStatus.OK);
    }

}
