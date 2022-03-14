package io.pismo.app.service

import io.pismo.app.AppApplicationTests
import io.pismo.app.exception.AccountsExistsDocumentNumberException
import io.pismo.app.exception.AccountsNotFoundException
import io.pismo.app.exception.AccountsWithoutDocumentNumberException
import io.pismo.app.model.Accounts
import io.pismo.app.repository.AccountsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import static org.junit.jupiter.api.Assertions.*

class AccountsServiceTest extends AppApplicationTests {

    @Autowired
    AccountsService accountsService

    @Autowired
    AccountsRepository accountsRepository

    Accounts accounts

    @BeforeEach
    void clearDb() {
        accounts = accountsRepository.deleteAll()
    }

    private static createBaseAccountObject() {
        return new Accounts(documentNumber: "12345678900")
    }

    @Test
    void Accounts_TestCreateWithSuccess(){
        Accounts account = createBaseAccountObject()
        Accounts accountCreated = accountsService.create(account)

        assertTrue(accountCreated.id > 0)
        assertEquals(accountCreated.documentNumber, account.documentNumber)

    }

    @Test
    void Accounts_TestCreateWithExistsDocumentNumber(){
        Accounts account = createBaseAccountObject()
        accountsService.create(account)

        assertThrows(AccountsExistsDocumentNumberException.class, { accountsService.create(account) })
    }

    @Test
    void Accounts_TestCreateWithoutDocumentNumber(){
        Accounts account = new Accounts(documentNumber: null)

        assertThrows(AccountsWithoutDocumentNumberException.class, { accountsService.create(account) })
    }

    @Test
    void Accounts_TestCreateAndRecoveryId(){
        Accounts account = createBaseAccountObject()
        Accounts accountCreated = accountsService.create(account)
        Accounts accountRecovered = accountsService.findById(accountCreated.id)

        assertEquals(accountCreated.id, accountRecovered.id)
        assertEquals(accountCreated.documentNumber, accountRecovered.documentNumber)

    }

    @Test
    void Accounts_TestRecoveryInvalidId(){
        assertThrows(AccountsNotFoundException.class, { accountsService.findById(Integer.MAX_VALUE) })
    }

}
