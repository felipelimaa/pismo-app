package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class TransactionsEmptyValueException extends ResponseStatusException {
    TransactionsEmptyValueException() {
        super(HttpStatus.BAD_REQUEST, "Fields required is empty in transaction.")
    }
}
