package com.freetreechair.data.util

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}
