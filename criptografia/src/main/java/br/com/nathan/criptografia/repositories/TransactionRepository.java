package br.com.nathan.criptografia.repositories;

import br.com.nathan.criptografia.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
