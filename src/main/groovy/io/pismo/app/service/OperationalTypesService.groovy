package io.pismo.app.service

import io.pismo.app.exception.InvalidOperationalTypesException
import io.pismo.app.model.OperationalTypes
import io.pismo.app.repository.OperationalTypesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OperationalTypesService {

    @Autowired
    OperationalTypesRepository operationalTypesRepository

    OperationalTypes findById(Long id){
        return operationalTypesRepository.findById(id).orElseThrow{ new InvalidOperationalTypesException() }
    }

}
