package io.pismo.app.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.pismo.app.AppApplicationTests
import io.pismo.app.model.Accounts
import io.pismo.app.repository.AccountsRepository
import io.pismo.app.service.AccountsService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.junit.jupiter.api.Assertions.*
import static io.pismo.app.exception.ProblemType.*

class AccountsControllerTest extends AppApplicationTests {

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    AccountsService accountsService

    @Autowired
    AccountsRepository accountsRepository

    private clearDb() {
        accountsRepository.deleteAll()
    }

    private static createBaseAccountObject() {
        return new Accounts(documentNumber: "12345678900")
    }

    @Test
    void Accounts_TestCreateWithSuccess() {
        clearDb()

        Accounts account = createBaseAccountObject()

        def response = mvc
            .perform(
                post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andReturn().response

        Accounts accountReturned = objectMapper.readerFor(Accounts.class).readValue(response.contentAsString)

        assertNotNull(accountReturned.id)
        assertEquals(accountReturned.documentNumber, account.documentNumber)

    }

    @Test
    void Accounts_TestCreateWithExistsDocumentNumber(){
        clearDb()

        Accounts account = createBaseAccountObject()

        accountsService.create(account)

        def response = mvc
            .perform(
                post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isConflict())
            .andReturn().response

        assertEquals(response.errorMessage, ACCOUNT_EXISTS_DOCUMENT_NUMBER.message)
    }

    @Test
    void Accounts_TestCreateWithoutDocumentNumber(){
        clearDb()

        Accounts account = new Accounts(documentNumber: null)

        def response = mvc
            .perform(
                post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andReturn().response

        assertEquals(response.errorMessage, ACCOUNT_WITHOUT_DOCUMENT_NUMBER.message)
    }

    @Test
    void Accounts_TestCreateAndRecoveryId() {
        clearDb()
        Accounts accounts = createBaseAccountObject()

        Accounts accountCreated = accountsService.create(accounts)

        def response = mvc
            .perform(get("/accounts/${accountCreated.id}"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn().response

        Accounts accountReturned = objectMapper.readerFor(Accounts.class).readValue(response.contentAsString)

        assertEquals(accountReturned.id, accountCreated.id)
        assertEquals(accountReturned.documentNumber, accountCreated.documentNumber)

    }

    @Test
    void Accounts_TestRecoveryInvalidId(){
        clearDb()

        def response = mvc
            .perform(get("/accounts/${Integer.MAX_VALUE}"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isNotFound())
            .andReturn().response

        assertEquals(response.errorMessage, ACCOUNT_NOT_FOUND.message)
    }


}
