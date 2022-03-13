package io.pismo.app.exception

import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class AccountsWithoutDocumentNumberException extends ResponseStatusException{
    AccountsWithoutDocumentNumberException() {
        super(ACCOUNT_WITHOUT_DOCUMENT_NUMBER.status, ACCOUNT_WITHOUT_DOCUMENT_NUMBER.message)
    }
}
