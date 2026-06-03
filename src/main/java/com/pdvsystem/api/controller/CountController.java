package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.count.Count;
import com.pdvsystem.api.domain.count.CountRequestDTO;
import com.pdvsystem.api.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/count")
public class CountController {

    @Autowired
    private CountService countService;

    // GET - api/count/{id} - Busca uma conta por id
    @GetMapping("/{id}")
    public ResponseEntity<Count> getCountByID(@PathVariable UUID id) {
        Count count = countService.getCountPayByID(id);
        return ResponseEntity.ok(count);
    }

    // GET - api/count/recieve - Busca as contas a receber
    @GetMapping("/recieve")
    public ResponseEntity<List<Count>> getAllCountRecieve() {
        List <Count> counts = countService.getAllCountRecieve();
        return ResponseEntity.ok(counts);
    }

    //GET - api/count/pay - Busca as contas a pagar
    @GetMapping("/pay")
    public ResponseEntity<List<Count>> getAllCountPay() {
        List<Count> counts = countService.getAllCountPay();
        return ResponseEntity.ok(counts);
    }

    //POST - api/count/pay - Cria uma nova conta a pagar
    @PostMapping("/pay")
    public ResponseEntity<Count> createCountPay(@RequestBody CountRequestDTO body) {
        Count count = countService.createCountPay(body);
        return ResponseEntity.ok(count);
    }

    //POST - api/count/recieve - Cria uma nova conta a pagar
    @PostMapping("/recieve")
    public ResponseEntity<Count> createCountRecieve(@RequestBody CountRequestDTO body) {
        Count count = countService.createCountRecieve(body);
        return ResponseEntity.ok(count);
    }

    //PUT - api/count/{id} - Edita uma conta
    @PutMapping("/{id}")
    public ResponseEntity<Count> editCount(@RequestBody CountRequestDTO body, @PathVariable UUID id) {
        Count count = countService.editCount(id,body);
        return ResponseEntity.ok(count);
    }

    //DELETE - api/count/{id} - Deleta uma conta
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCount(@PathVariable UUID id) {
        countService.deletCount(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }
}
