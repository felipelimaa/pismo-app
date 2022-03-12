package io.pismo.app.controller

import io.pismo.app.model.Accounts
import io.pismo.app.service.AccountsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountsController {

    @Autowired
    AccountsService accountsService

    @GetMapping("/{id}")
    ResponseEntity<Accounts> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(accountsService.findById(id))
    }

    @PostMapping
    ResponseEntity<Accounts> create(@RequestBody Accounts accounts) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountsService.create(accounts))
    }

}
