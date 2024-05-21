package br.com.nathan.criptografia.entities;


import br.com.nathan.criptografia.services.CryptoService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "user_document")
    private String encryptedUserDocument;

    @Column(name = "credit_card_token")
    private String encryptedCreditCardToken;

    @Column(name = "transaction_value")
    private Long transactionValue;

    @Transient /*Usada quando temos um campo que não é de fato armazenado no banco de dados. */
    private String rawUserDocument;

    @Transient
    private String rawCreditCardToken;

    @PrePersist /* Esse método será executado antes de armazenar essa entidade no banco de dados
     fazendo a criptografia do dado NÃO CRIPTOGRAFADO, antes de armazenar. Armazenando o dado já
     CRIPTOGRAFADO. */
    public void prePersist(){
        this.encryptedUserDocument = CryptoService.encrypt(rawUserDocument);
        this.encryptedCreditCardToken = CryptoService.encrypt(rawCreditCardToken);
    }

    @PostLoad /*Vai DESCRIPTOGRAFAR os campos e deixar o valor "raw" disponivel nos 2 campos "transients".*/
    public void postLoad(){
        this.rawUserDocument = CryptoService.decrypt(encryptedUserDocument);
        this.rawCreditCardToken = CryptoService.decrypt(encryptedCreditCardToken);
    }
}
