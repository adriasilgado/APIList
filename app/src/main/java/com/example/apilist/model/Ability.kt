package com.example.apilist.model

data class Ability(
    val description: String,
    val displayIcon: String,
    val displayName: String
){
    fun abilityToString():String{
        return "$description-$displayIcon-$displayName"
    }
}

