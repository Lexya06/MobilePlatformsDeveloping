package com.github.lexya06.culinaryguide.data.repository


import android.content.Context
import com.github.lexya06.culinaryguide.data.dao.IUserDao
import com.github.lexya06.culinaryguide.data.dao.UserDao
import com.github.lexya06.culinaryguide.data.model.User
import com.github.lexya06.culinaryguide.data.sqlite.SQLiteProvider

class UserRepository(context: Context) {

    private val dao: IUserDao =
        UserDao(SQLiteProvider(context))

    fun insert(user: User) = dao.insert(user)

    fun getById(id: Long) = dao.getById(id)

    fun getAll() = dao.getAll()

    fun update(user: User) = dao.update(user)

    fun delete(id: Long) = dao.delete(id)
}