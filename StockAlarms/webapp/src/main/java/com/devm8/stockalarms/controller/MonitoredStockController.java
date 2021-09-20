package com.devm8.stockalarms.controller;

import com.devm8.stockalarms.decoder.UserJWTDecoder;
import com.devm8.stockalarms.model.MonitoredStockDTO;
import com.devm8.stockalarms.service.MonitoredStockServiceImpl;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devm8.stockalarms.decoder.UserJWTDecoder.getUserIdFromToken;

@RestController
@RequestMapping(path = "/monitoring")
public class MonitoredStockController {

    @Autowired
    MonitoredStockServiceImpl monitoredStockService;

    @GetMapping("/{id}")
    public ResponseEntity<MonitoredStockDTO> findById(@PathVariable int id, @RequestHeader(value = "jwt") String header) {
        MonitoredStockDTO monitoredStockDTO = monitoredStockService.getMonitoredStockById(id);
        try {
            if (isJWTOk(header) && isAllowed(monitoredStockDTO.getUserId(), header)) {
                return new ResponseEntity<>(monitoredStockDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (SignatureException | MalformedJwtException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<MonitoredStockDTO> createMonitoredStock(@RequestBody MonitoredStockDTO monitoredStockDTO, @RequestHeader(value = "jwt") String header) {
        try {
            if (isJWTOk(header) && isAllowed(monitoredStockDTO.getUserId(), header)) {
                return new ResponseEntity<>(monitoredStockService.createMonitoredStock(monitoredStockDTO), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (SignatureException | MalformedJwtException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitoredStock(@PathVariable int id, @RequestHeader(value = "jwt") String header) {
        MonitoredStockDTO monitoredStockDTO = monitoredStockService.getMonitoredStockById(id);
        try {
            if (isJWTOk(header) && isAllowed(monitoredStockDTO.getUserId(), header)) {
                monitoredStockService.deleteMonitoredStock(id);

            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (SignatureException | MalformedJwtException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean isAllowed(int userId, String header) throws SignatureException, MalformedJwtException {
        if (header == null || header.isEmpty() || header.isBlank()) {
            return false;
        }
        return userId == getUserIdFromToken(header);
    }

    private boolean isJWTOk(String jwt) {
        if (jwt.isEmpty()) {
            return false;
        }
        return !UserJWTDecoder.decodeJWT(jwt).isEmpty();
    }

}
