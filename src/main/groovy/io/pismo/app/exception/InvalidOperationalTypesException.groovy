package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class InvalidOperationalTypesException extends ResponseStatusException {
    InvalidOperationalTypesException() {
        super(OPERATIONAL_TYPES_INVALID.status, OPERATIONAL_TYPES_INVALID.message)
    }
}
