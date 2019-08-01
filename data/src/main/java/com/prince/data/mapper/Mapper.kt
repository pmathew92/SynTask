package com.prince.data.mapper

interface Mapper<T, E> {

    fun from(t: T): E
}