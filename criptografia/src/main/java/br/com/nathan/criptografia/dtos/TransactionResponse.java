package br.com.nathan.criptografia.dtos;

import br.com.nathan.criptografia.entities.TransactionEntity;

public record TransactionResponse(
        Long transactionId,
        String userDocument,
        String creditCardToken,
        Long transactionValue

) {

    public static TransactionResponse fromEntity(TransactionEntity transactionEntity){
        return new TransactionResponse(
                transactionEntity.getTransactionId(),
                transactionEntity.getRawUserDocument(),
                transactionEntity.getRawCreditCardToken(),
                transactionEntity.getTransactionValue()

        );

    }
}
