package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class TransactionsValueLessThanZeroException extends ResponseStatusException{
    TransactionsValueLessThanZeroException() {
        super(TRANSACTION_VALUE_LESS_THAN_ZERO.status, TRANSACTION_VALUE_LESS_THAN_ZERO.message)
    }
}
