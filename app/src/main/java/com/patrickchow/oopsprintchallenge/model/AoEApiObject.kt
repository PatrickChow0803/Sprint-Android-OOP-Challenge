package com.patrickchow.oopsprintchallenge.model

import java.io.Serializable

//This class holds attributes that all the child classes hold.
//Serializable because the item gets placed into a Bundle *Happens at ItemListActivity
abstract class AoEApiObject(
    open val id: Int ?= null,
    open val name: String ?= null,
    open val expansion: String ?= null,
    open val favorite: Boolean = false //Personal data for the assignment. Not actually part of API
):Serializable{
    open fun info(): String = "An Age of Empire object"

    override fun toString(): String {
        return "AoEAPIObject(id=$id, name=$name, expansion=$expansion, favorite=$favorite)"
    }
}