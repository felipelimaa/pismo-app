package io.pismo.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class InvalidOperationalTypesException extends ResponseStatusException {
    InvalidOperationalTypesException() {
        super(HttpStatus.NOT_FOUND, "Operational Types not found!")
    }
}
