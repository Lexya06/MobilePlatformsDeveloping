package com.github.lexya06.culinaryguide.data.sqlite

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory

class SQLiteProvider(context: Context) {

    private val helper: SupportSQLiteOpenHelper

    init {
        val config = SupportSQLiteOpenHelper.Configuration.builder(context)
            .name("culinary_guide.db")
            .callback(object : SupportSQLiteOpenHelper.Callback(1) {

                override fun onCreate(db: SupportSQLiteDatabase) {
                    db.execSQL(UserTable.CREATE_SQL)
                }

                override fun onUpgrade(
                    db: SupportSQLiteDatabase,
                    oldVersion: Int,
                    newVersion: Int
                ) {
                    db.execSQL("DROP TABLE IF EXISTS ${UserTable.TABLE}")
                    onCreate(db)
                }
            })
            .build()

        helper = FrameworkSQLiteOpenHelperFactory().create(config)
    }

    fun writable(): SupportSQLiteDatabase = helper.writableDatabase
    fun readable(): SupportSQLiteDatabase = helper.readableDatabase
}