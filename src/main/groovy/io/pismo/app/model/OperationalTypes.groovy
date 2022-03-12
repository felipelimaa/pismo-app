package io.pismo.app.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@ToString(includePackage = false, includeNames = true)
@EqualsAndHashCode
class OperationalTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operationtype_id")
    @JsonProperty("operationtype_id")
    Long id

    @Column(name = "description")
    @JsonProperty("description")
    String description

    @Column(name = "operation_decrease")
    @JsonProperty("operation_decrease")
    String operationDecrease

}
