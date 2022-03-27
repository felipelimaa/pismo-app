package io.pismo.app.exception

import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class AccountWithoutCreditAvailableException extends ResponseStatusException{
    AccountWithoutCreditAvailableException(){
        super(ACCOUNT_WITHOUT_CREDIT_AVAILABLE.status, ACCOUNT_WITHOUT_CREDIT_AVAILABLE.message)
    }
}
