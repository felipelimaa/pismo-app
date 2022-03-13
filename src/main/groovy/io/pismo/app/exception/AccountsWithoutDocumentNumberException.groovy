package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class AccountsWithoutDocumentNumberException extends ResponseStatusException{
    AccountsWithoutDocumentNumberException() {
        super(HttpStatus.CONFLICT, "Document number is required")
    }
}
