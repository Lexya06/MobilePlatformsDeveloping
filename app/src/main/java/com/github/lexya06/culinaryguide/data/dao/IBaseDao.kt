package com.github.lexya06.culinaryguide.data.dao

interface IBaseDao<T> {

    fun insert(entity: T): Long

    fun getById(id: Long): T?

    fun getAll(): List<T>

    fun update(entity: T): Int

    fun delete(id: Long): Int
}