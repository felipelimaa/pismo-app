package io.pismo.app.repository

import io.pismo.app.model.Accounts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository extends JpaRepository<Accounts, Long>{
    Accounts findFirstByDocumentNumber(String documentNumber)
}