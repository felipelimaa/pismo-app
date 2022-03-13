package io.pismo.app.exception

import org.springframework.http.HttpStatus

enum ProblemType {
    ACCOUNT_WITHOUT_DOCUMENT_NUMBER(HttpStatus.BAD_REQUEST, "Document number is required"),
    ACCOUNT_EXISTS_DOCUMENT_NUMBER(HttpStatus.CONFLICT, "Already exists a document number"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "Accounts not found!"),
    OPERATIONAL_TYPES_INVALID(HttpStatus.NOT_FOUND, "Operational Types not found!"),
    TRANSACTION_EMPTY_VALUE(HttpStatus.BAD_REQUEST, "Fields required is empty in transaction."),
    TRANSACTION_VALUE_LESS_THAN_ZERO(HttpStatus.BAD_REQUEST, "The value of transaction is less than zero")

    HttpStatus status

    String message

    ProblemType(status, message){
        this.status = status
        this.message = message
    }

}