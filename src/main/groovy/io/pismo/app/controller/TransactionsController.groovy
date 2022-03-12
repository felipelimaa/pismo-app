package io.pismo.app.controller

import io.pismo.app.dto.TransactionsDTO
import io.pismo.app.model.Transactions
import io.pismo.app.service.TransactionsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionsController {

    @Autowired
    TransactionsService transactionsService

    @PostMapping
    ResponseEntity<TransactionsDTO> create(@RequestBody TransactionsDTO transactions) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionsService.create(transactions))
    }

}
