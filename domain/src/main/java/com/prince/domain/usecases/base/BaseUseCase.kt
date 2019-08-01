package com.prince.domain.usecases.base

abstract class BaseUseCase<T> {
    abstract suspend fun execute(): T
}