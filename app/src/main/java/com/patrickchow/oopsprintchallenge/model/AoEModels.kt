package com.patrickchow.oopsprintchallenge.model

/*
Civilization -
    id: Int
    name: String
    expansion: String,
    army_type: String,
    unique_unit: String
    unique_tech: String
 */
data class Civilizations(
    val army_type: String,
    val unique_unit: String,
    val unique_tech: String
):AoEApiObject(){
    override fun info(): String = "Army Type: $army_type,\n" +
                                  "Unique Unit: $unique_unit,\n" +
                                  "Unique Tech: $unique_tech"

    override fun toString(): String {
        return "$id, $name, $expansion, $army_type, $unique_unit, $unique_tech"
    }
}

/*
Unit -
    id: Int,
    name: Int,
    expansion: String,
    age: String,
    created_in: String,
    build_time: Int
 */

data class Unit(
    val age: String,
    val created_in: String,
    val build_time: Int
):AoEApiObject(){
    override fun info(): String = "Age: $age,\n" +
                                  "Created In: $created_in,\n" +
                                  "Build Time: $build_time"

    override fun toString(): String {
        return "$id, $name, $expansion, $age, $created_in, $build_time"
    }
}
/*
Structure -
    id: Int,
    name: String,
    expansion: String,
    age: String,
    build_time: Int
    hit_points: Int
 */
data class Structure(
    val age: String,
    val build_time: Int,
    val hit_points: Int
):AoEApiObject(){
    override fun info(): String = "Age: $age,\n" +
                                  "Build Time: $build_time,\n" +
                                  "Hit Points: $hit_points"

    override fun toString(): String {
        return "$id, $name, $expansion, $age, $build_time, $hit_points"
    }
}

/*
Technology
    id: Int
    name: String,
    expansion: String,
    age: String,
    develops_in: String,
    build_time: Int
 */

data class Technology(
    val age: String,
    val develops_in: String,
    val build_time: Int
):AoEApiObject(){
    override fun info(): String = "Age: $age,\n" +
                                  "Develops In: $develops_in,\n" +
                                  "Build Time: $build_time"

    override fun toString(): String {
        return "$id, $name, $expansion, $age, $develops_in, $build_time"
    }
}