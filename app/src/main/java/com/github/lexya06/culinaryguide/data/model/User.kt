package com.github.lexya06.culinaryguide.data.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class User @OptIn(ExperimentalTime::class) constructor(
    var userId: Long,
    var userLogin: String,
    var userEmail: String,
    var userPasswHash: String,
    var userAge: Byte,
    var userDateReg: Instant,
    var userImagePath: String
)