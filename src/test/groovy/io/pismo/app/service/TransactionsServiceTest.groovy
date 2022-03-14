package io.pismo.app.service

import io.pismo.app.AppApplicationTests
import io.pismo.app.dto.TransactionsDTO
import io.pismo.app.exception.AccountsNotFoundException
import io.pismo.app.exception.InvalidOperationalTypesException
import io.pismo.app.exception.TransactionsEmptyValueException
import io.pismo.app.exception.TransactionsValueLessThanZeroException
import io.pismo.app.model.Accounts
import io.pismo.app.model.Transactions
import io.pismo.app.repository.AccountsRepository
import io.pismo.app.repository.TransactionsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import static org.junit.jupiter.api.Assertions.*

class TransactionsServiceTest extends AppApplicationTests {

    @Autowired
    AccountsRepository accountsRepository

    @Autowired
    TransactionsRepository transactionsRepository

    @Autowired
    AccountsService accountsService

    @Autowired
    TransactionsService transactionsService

    Transactions transactions

    Accounts accounts

    @BeforeEach
    void clearDb() {
        transactions = transactionsRepository.deleteAll()
        accounts = accountsRepository.deleteAll()
    }

    private static createBaseAccountObject() {
        return new Accounts(documentNumber: "12345678900")
    }

    private static createBaseTransactionDTOObject(Long accountId, Long operationalTypeId, BigDecimal amount) {
        return new TransactionsDTO(accountId: accountId, operationTypeId: operationalTypeId, amount: amount)
    }

    @Test
    void Transactions_TestCreateWithSuccess() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, 4, new BigDecimal(10))

        TransactionsDTO transactionsDTOCreated = transactionsService.create(transactionsDTO)

        assertNotNull(transactionsDTOCreated.id)
        assertEquals(transactionsDTOCreated.accountId, transactionsDTO.accountId)
        assertEquals(transactionsDTOCreated.operationTypeId, transactionsDTO.operationTypeId)
        assertTrue(transactionsDTOCreated.amount > 0)
        assertNotNull(transactionsDTOCreated.eventDate)
    }

    @Test
    void Transactions_TestCreateWithNegativeOperationSuccess() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, 1, new BigDecimal(10))

        TransactionsDTO transactionsDTOCreated = transactionsService.create(transactionsDTO)

        assertNotNull(transactionsDTOCreated.id)
        assertEquals(transactionsDTOCreated.accountId, transactionsDTO.accountId)
        assertEquals(transactionsDTOCreated.operationTypeId, transactionsDTO.operationTypeId)
        assertTrue(transactionsDTOCreated.amount < 0)
        assertNotNull(transactionsDTOCreated.eventDate)
    }

    @Test
    void Transactions_TestCreateWithInvalidOperationalType() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, Integer.MAX_VALUE, new BigDecimal(10))

        assertThrows( InvalidOperationalTypesException.class, { transactionsService.create(transactionsDTO) } )
    }

    @Test
    void Transactions_TestCreateWithInvalidAccountId() {
        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(Integer.MAX_VALUE, 1, new BigDecimal(10))

        assertThrows( AccountsNotFoundException.class, { transactionsService.create(transactionsDTO) } )
    }

    @Test
    void Transactions_TestCreateWithValueLessThanZero() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, 1, new BigDecimal(-10))

        assertThrows( TransactionsValueLessThanZeroException.class, { transactionsService.create( transactionsDTO) } )
    }

    @Test
    void Transactions_TestCreateWithEmptyValue(){
        TransactionsDTO transactionsDTO = new TransactionsDTO()

        assertThrows( TransactionsEmptyValueException.class, { transactionsService.create( transactionsDTO) } )
    }



}
