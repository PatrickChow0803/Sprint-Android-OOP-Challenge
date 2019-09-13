package com.patrickchow.oopsprintchallenge.model

//This class holds attributes that all the child classes hold. (Exception being age for civilization)
abstract class AoEModels(
    val id: Int? = null,
    val name: String? = null,
    val expansion: String? =null,
    val age: String? = null, //Civilization doesn't have an age attribute but all the other classes do.
    var favorite: Boolean = false //Personal data for the assignment. Not actually part of API
)

class Civilizations(
    id: Int,
    name: String,
    expansion: String
):AoEModels(id, name, expansion)

class Unit(
    id: Int,
    name: String,
    expansion: String,
    age: String
):AoEModels(id, name, age, expansion)

class Structure(
    id: Int,
    name: String,
    expansion: String,
    age: String
):AoEModels(id, name, age, expansion)

class Technology(
    id: Int,
    name: String,
    expansion: String,
    age: String
):AoEModels(id, name, age, expansion)