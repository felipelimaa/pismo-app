package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class TransactionsValueLessThanZero extends ResponseStatusException{
    TransactionsValueLessThanZero() {
        super(HttpStatus.BAD_REQUEST, "The value of transaction is less than zero")
    }
}
