package com.github.lexya06.culinaryguide.data.dao

import androidx.sqlite.db.SimpleSQLiteQuery
import com.github.lexya06.culinaryguide.data.sqlite.EntityMapper
import com.github.lexya06.culinaryguide.data.sqlite.SQLiteProvider

abstract class BaseDao<T>(
    private val provider: SQLiteProvider,
    private val tableName: String,
    private val idColumn: String,
    private val mapper: EntityMapper<T>
) : IBaseDao<T> {

    override fun insert(entity: T): Long {
        return provider.writable().insert(
            tableName,
            0,
            mapper.toContentValues(entity)
        )
    }

    override fun getById(id: Long): T? {
        val query = SimpleSQLiteQuery(
            "SELECT * FROM $tableName WHERE $idColumn = ?",
            arrayOf(id)
        )

        provider.readable().query(query).use { cursor ->
            return if (cursor.moveToFirst()) {
                mapper.fromCursor(cursor)
            } else null
        }
    }

    override fun getAll(): List<T> {
        val result = mutableListOf<T>()

        val query = SimpleSQLiteQuery(
            "SELECT * FROM $tableName ORDER BY $idColumn DESC"
        )

        provider.readable().query(query).use { cursor ->
            while (cursor.moveToNext()) {
                result.add(mapper.fromCursor(cursor))
            }
        }

        return result
    }

    override fun update(entity: T): Int {
        val id = extractId(entity)

        return provider.writable().update(
            tableName,
            0,
            mapper.toContentValues(entity),
            "$idColumn = ?",
            arrayOf(id)
        )
    }

    override fun delete(id: Long): Int {
        return provider.writable().delete(
            tableName,
            "$idColumn = ?",
            arrayOf(id)
        )
    }

    protected abstract fun extractId(entity: T): Long
}