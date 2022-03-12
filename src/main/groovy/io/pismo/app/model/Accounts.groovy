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
class Accounts {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("account_id")
    Long id

    @Column(name = "document_number", nullable = false)
    @JsonProperty(value = "document_number", required = true)
    String documentNumber

}
