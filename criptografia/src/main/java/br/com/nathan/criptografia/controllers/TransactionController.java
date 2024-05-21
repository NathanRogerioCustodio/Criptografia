package br.com.nathan.criptografia.controllers;

import br.com.nathan.criptografia.dtos.CreateTransactionRequest;
import br.com.nathan.criptografia.dtos.TransactionResponse;
import br.com.nathan.criptografia.dtos.UpdateTransactionRequest;
import br.com.nathan.criptografia.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping (value = "transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController( TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request){
        transactionService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll (@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(name= "pageSize", defaultValue = "20") Integer pageSize){
        var responseBody = transactionService.listAll(page, pageSize);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable (value = "id") Long id){
        var responseBody = transactionService.findById(id);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable (value = "id")Long id,
                                       @RequestBody UpdateTransactionRequest request){
        transactionService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id){
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
