package br.com.nathan.criptografia.services;

import br.com.nathan.criptografia.dtos.CreateTransactionRequest;
import br.com.nathan.criptografia.dtos.TransactionResponse;
import br.com.nathan.criptografia.dtos.UpdateTransactionRequest;
import br.com.nathan.criptografia.entities.TransactionEntity;
import br.com.nathan.criptografia.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    public void create(CreateTransactionRequest request){
        var entity = new TransactionEntity();
        entity.setRawCreditCardToken(request.creditCardToken());
        entity.setRawUserDocument(request.userDocument());
        entity.setTransactionValue(request.transactionValue());

        transactionRepository.save(entity);
    }

    public Page<TransactionResponse> listAll(int page, int pageSize){
        var content = transactionRepository.findAll(PageRequest.of(page, pageSize));
        return content.map(TransactionResponse::fromEntity);
    }

    public TransactionResponse findById(Long id){
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionResponse.fromEntity(entity);
    }

    public void update(Long id, UpdateTransactionRequest request){
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setTransactionValue(request.transactionValue());
        transactionRepository.save(entity);
    }

    public void deleteById(Long id) {
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        transactionRepository.deleteById(entity.getTransactionId());
    }
}
