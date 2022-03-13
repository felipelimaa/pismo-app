package io.pismo.app.exception

import org.springframework.web.server.ResponseStatusException

import static io.pismo.app.exception.ProblemType.*

class AccountsNotFoundException extends ResponseStatusException {
    AccountsNotFoundException() {
        super(ACCOUNT_NOT_FOUND.status, ACCOUNT_NOT_FOUND.message)
    }
}
