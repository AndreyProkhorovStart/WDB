package com.train.controllers;

import com.train.services.GroupsService;
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
 * Контроллер Групп
 * /groups - Получить сведения о группах
 * /groups/{id} - Получить группу по id
 */

@RestController
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    /**
     * Получение всех записей
     *
     * @return
     */
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Получить сведения о группах", notes = "Получить сведения о группах")
    @GetMapping(value = "/groups")
    public ResponseEntity<Object> index() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data", groupsService.getAll());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * Получение конкретной записи
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/groups/{id}")
    @ApiOperation(value = "Получить группу по id", notes = "Получить группу по id")
    public ResponseEntity<Object> show(@PathVariable(value = "id", required = true) Integer id) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("data", groupsService.read(id));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
