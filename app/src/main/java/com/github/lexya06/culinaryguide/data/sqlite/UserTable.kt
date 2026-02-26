package com.github.lexya06.culinaryguide.data.sqlite

object UserTable {

    const val TABLE = "users"

    const val COL_ID = "user_id"
    const val COL_LOGIN = "user_login"
    const val COL_EMAIL = "user_email"
    const val COL_PASS_HASH = "user_pass_hash"
    const val COL_AGE = "user_age"
    const val COL_DATE_REG = "user_date_reg"
    const val COL_IMAGE_PATH = "user_image_path"

    val CREATE_SQL = """
        CREATE TABLE IF NOT EXISTS $TABLE (
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_LOGIN TEXT NOT NULL,
            $COL_EMAIL TEXT NOT NULL,
            $COL_PASS_HASH TEXT NOT NULL,
            $COL_AGE INTEGER NOT NULL,
            $COL_DATE_REG INTEGER NOT NULL,
            $COL_IMAGE_PATH TEXT NOT NULL
        )
    """.trimIndent()
}