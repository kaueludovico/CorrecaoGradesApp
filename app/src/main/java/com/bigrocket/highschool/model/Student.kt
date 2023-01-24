package com.bigrocket.highschool.model

import java.io.Serializable

data class Student(
    var name: String,
    var matter: String,
    var noteOne: Float,
    var noteTwo: Float,
    var noteThree: Float,
    var noteFour: Float
) : Serializable
