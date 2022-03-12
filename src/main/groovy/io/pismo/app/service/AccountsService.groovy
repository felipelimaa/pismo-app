package io.pismo.app.service

import io.pismo.app.exception.AccountsNotFoundException
import io.pismo.app.exception.AccountsWithoutDocumentNumberException
import io.pismo.app.model.Accounts
import io.pismo.app.repository.AccountsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional

@Service
class AccountsService {

    @Autowired
    AccountsRepository accountsRepository

    Accounts findById(Long id) {
        return accountsRepository.findById(id).orElseThrow{new AccountsNotFoundException()}
    }

    @Transactional
    Accounts create(Accounts accounts) {
        if(accountsRepository.findFirstByDocumentNumber(accounts.documentNumber)) {
            throw new AccountsWithoutDocumentNumberException()
        }

        return accountsRepository.save(accounts)
    }

}
