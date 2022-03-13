package io.pismo.app.exception

import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class AccountsExistsDocumentNumberException extends ResponseStatusException{
    AccountsExistsDocumentNumberException() {
        super(ACCOUNT_EXISTS_DOCUMENT_NUMBER.status, ACCOUNT_EXISTS_DOCUMENT_NUMBER.message)
    }
}
