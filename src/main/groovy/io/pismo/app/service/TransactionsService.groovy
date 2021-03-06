package io.pismo.app.service

import io.pismo.app.dto.TransactionsDTO
import io.pismo.app.exception.AccountWithoutCreditAvailableException
import io.pismo.app.exception.TransactionsEmptyValueException
import io.pismo.app.exception.TransactionsValueLessThanZeroException
import io.pismo.app.model.Transactions
import io.pismo.app.repository.TransactionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository

    @Autowired
    AccountsService accountsService

    @Autowired
    OperationalTypesService operationalTypes

    @Transactional
    TransactionsDTO create(TransactionsDTO transactionsDTO) {
        if(
            transactionsDTO.accountId == null
            && transactionsDTO.operationTypeId == null
            && transactionsDTO.amount == null
        ) {
            throw new TransactionsEmptyValueException()
        }

        if(transactionsDTO.amount <= 0) {
            throw new TransactionsValueLessThanZeroException()
        }

        def accounts = accountsService.findById(transactionsDTO.accountId)
        def operationalTypes = operationalTypes.findById(transactionsDTO.operationTypeId)

        def transactions = new Transactions(accounts: accounts, operationalTypes: operationalTypes)

        def calculatedValue = transactionsDTO.amount

        if(operationalTypes.operationDecrease.toBoolean()){
            calculatedValue *= -1
        }

        def transactionAccountLimit = accounts.creditLimit + calculatedValue

        if(transactionAccountLimit < 0) {
            throw new AccountWithoutCreditAvailableException()
        }


        transactions.amount = calculatedValue
        transactionsDTO.amount = calculatedValue

        accountsService.alterCreditLimit(accounts, transactionAccountLimit)

        transactionsRepository.save(transactions)

        transactionsDTO.id = transactions.id
        transactionsDTO.eventDate = transactions.eventDate
        transactionsDTO.creditLimit = transactionAccountLimit

        return transactionsDTO
    }
}
