package br.com.nathan.criptografia.dtos;

public record CreateTransactionRequest(
        String userDocument,
        String creditCardToken,
        Long transactionValue
) {
}
