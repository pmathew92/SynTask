package com.prince.syntask.mapper

interface Mapper<T, E> {

    fun from(t: T): E
}