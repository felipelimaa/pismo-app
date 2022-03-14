package io.pismo.app.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.pismo.app.AppApplicationTests
import io.pismo.app.dto.TransactionsDTO
import io.pismo.app.model.Accounts
import io.pismo.app.model.Transactions
import io.pismo.app.repository.AccountsRepository
import io.pismo.app.repository.TransactionsRepository
import io.pismo.app.service.AccountsService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.junit.jupiter.api.Assertions.*
import static io.pismo.app.exception.ProblemType.*

class TransactionsControllerTest extends AppApplicationTests{

    @Autowired
    AccountsRepository accountsRepository

    @Autowired
    TransactionsRepository transactionsRepository

    @Autowired
    AccountsService accountsService

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

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

        def response = mvc
            .perform(
                post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionsDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andReturn().response

        TransactionsDTO transactionsDTOReturned = objectMapper.readerFor(TransactionsDTO.class).readValue(response.contentAsString)

        assertEquals(transactionsDTOReturned.accountId, transactionsDTO.accountId)
        assertEquals(transactionsDTOReturned.operationTypeId, transactionsDTO.operationTypeId)
        assertTrue(transactionsDTOReturned.amount > 0)
        assertNotNull(transactionsDTOReturned.id)
        assertNotNull(transactionsDTOReturned.eventDate)
    }

    @Test
    void Transactions_TestCreateWithNegativeOperationSuccess() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, 1, new BigDecimal(10))

        def response = mvc
            .perform(
                post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionsDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andReturn().response

        TransactionsDTO transactionsDTOReturned = objectMapper.readerFor(TransactionsDTO.class).readValue(response.contentAsString)

        assertEquals(transactionsDTOReturned.accountId, transactionsDTO.accountId)
        assertEquals(transactionsDTOReturned.operationTypeId, transactionsDTO.operationTypeId)
        assertTrue(transactionsDTOReturned.amount < 0)
        assertNotNull(transactionsDTOReturned.id)
        assertNotNull(transactionsDTOReturned.eventDate)
    }

    @Test
    void Transactions_TestCreateWithInvalidOperationalType() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, Integer.MAX_VALUE, new BigDecimal(10))

        def response = mvc
            .perform(
                post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionsDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isNotFound())
            .andReturn().response

        assertEquals(response.errorMessage, OPERATIONAL_TYPES_INVALID.message)
    }

    @Test
    void Transactions_TestCreateWithInvalidAccountId() {
        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(Integer.MAX_VALUE, 1, new BigDecimal(10))

        def response = mvc
            .perform(
                post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionsDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isNotFound())
            .andReturn().response

        assertEquals(response.errorMessage, ACCOUNT_NOT_FOUND.message)
    }

    @Test
    void Transactions_TestCreateWithValueLessThanZero() {
        Accounts account = accountsService.create(createBaseAccountObject())

        TransactionsDTO transactionsDTO = createBaseTransactionDTOObject(account.id, 1, new BigDecimal(-10))

        def response = mvc
            .perform(
                post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionsDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andReturn().response

        assertEquals(response.errorMessage, TRANSACTION_VALUE_LESS_THAN_ZERO.message)
    }

    @Test
    void Transactions_TestCreateWithEmptyValue(){
        TransactionsDTO transactionsDTO = new TransactionsDTO()

        def response = mvc
            .perform(
                post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionsDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andReturn().response

        assertEquals(response.errorMessage, TRANSACTION_EMPTY_VALUE.message)
    }

}
