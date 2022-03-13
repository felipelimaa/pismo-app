package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class AccountsExistsDocumentNumberException extends ResponseStatusException{
    AccountsExistsDocumentNumberException() {
        super(HttpStatus.CONFLICT, "Already exists a document number")
    }
}
