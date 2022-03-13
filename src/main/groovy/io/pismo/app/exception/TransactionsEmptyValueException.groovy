package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class TransactionsEmptyValueException extends ResponseStatusException {
    TransactionsEmptyValueException() {
        super(TRANSACTION_EMPTY_VALUE.status, TRANSACTION_EMPTY_VALUE.message)
    }
}
