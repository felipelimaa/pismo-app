package io.pismo.app.repository

import io.pismo.app.model.Transactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionsRepository extends JpaRepository<Transactions, Long>{

}