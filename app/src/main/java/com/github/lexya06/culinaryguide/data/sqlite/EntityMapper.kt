package com.github.lexya06.culinaryguide.data.sqlite

import android.content.ContentValues
import android.database.Cursor

interface EntityMapper<T> {

    fun toContentValues(entity: T): ContentValues

    fun fromCursor(cursor: Cursor): T
}