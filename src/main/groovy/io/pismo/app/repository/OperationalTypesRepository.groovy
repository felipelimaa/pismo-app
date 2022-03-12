package io.pismo.app.repository

import io.pismo.app.model.OperationalTypes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationalTypesRepository extends JpaRepository<OperationalTypes, Long> {
}
