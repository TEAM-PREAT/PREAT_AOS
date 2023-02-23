package com.freetreechair.data

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}
