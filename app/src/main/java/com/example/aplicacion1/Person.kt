package com.example.aplicacion1

import java.util.UUID

class Person(val name:String, val age:Int) {
    val id : UUID = UUID.randomUUID()
}