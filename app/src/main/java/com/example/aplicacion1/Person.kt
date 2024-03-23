package com.example.aplicacion1

import java.util.UUID

class Person(var name:String, var age:Int) {
    val id : UUID = UUID.randomUUID()
}