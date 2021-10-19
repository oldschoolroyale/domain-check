package com.brm.domaincheck.model

data class Domain(
    val creationDate: String,
    val expirationDate: String,
    val isAvailable: Boolean,
    val name: String,
    val registrar: String,
    val status: List<String>,
    val updatedDate: String
)