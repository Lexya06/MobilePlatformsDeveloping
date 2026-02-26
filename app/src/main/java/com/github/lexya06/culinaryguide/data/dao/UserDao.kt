package com.github.lexya06.culinaryguide.data.dao

import kotlin.time.Instant
import android.database.Cursor
import android.content.ContentValues
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.lexya06.culinaryguide.data.model.User
import com.github.lexya06.culinaryguide.data.sqlite.EntityMapper
import com.github.lexya06.culinaryguide.data.sqlite.SQLiteProvider
import com.github.lexya06.culinaryguide.data.sqlite.UserTable
import kotlin.time.ExperimentalTime

class UserDao(
    provider: SQLiteProvider
) : BaseDao<User>(
    provider = provider,
    tableName = UserTable.TABLE,
    idColumn = UserTable.COL_ID,
    mapper = UserMapper
), IUserDao {

    override fun extractId(entity: User): Long = entity.userId
}
private object UserMapper : EntityMapper<User> {

    @OptIn(ExperimentalTime::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun toContentValues(entity: User): ContentValues =
        ContentValues().apply {
            put(UserTable.COL_LOGIN, entity.userLogin)
            put(UserTable.COL_EMAIL, entity.userEmail)
            put(UserTable.COL_PASS_HASH, entity.userPasswHash)
            put(UserTable.COL_AGE, entity.userAge.toInt())
            put(UserTable.COL_DATE_REG, entity.userDateReg.epochSeconds)
            put(UserTable.COL_IMAGE_PATH, entity.userImagePath)
        }

    @OptIn(ExperimentalTime::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun fromCursor(cursor: Cursor): User =
        User(
            userId = cursor.getLong(cursor.getColumnIndexOrThrow(UserTable.COL_ID)),
            userLogin = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COL_LOGIN)),
            userEmail = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COL_EMAIL)),
            userPasswHash = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COL_PASS_HASH)),
            userAge = cursor.getInt(cursor.getColumnIndexOrThrow(UserTable.COL_AGE)).toByte(),
            userDateReg = Instant.fromEpochSeconds(
                cursor.getLong(cursor.getColumnIndexOrThrow(UserTable.COL_DATE_REG)),0
            ),
            userImagePath = cursor.getString(cursor.getColumnIndexOrThrow(UserTable.COL_IMAGE_PATH))
        )
}