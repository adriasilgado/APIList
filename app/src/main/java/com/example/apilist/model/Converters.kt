package com.example.apilist.model

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListAbilityToString(abilities:List<Ability>):String {
        var result:String = ""
        for (i in 0 until abilities.size - 1) {
            result += abilities[i].abilityToString()
            result += "+"
        }
        result += abilities[abilities.size - 1].abilityToString()
        println(result)
        return result
    }

    @TypeConverter
    fun fromStringToListAbility(abilities:String):List<Ability> {
        var ability = abilities.split("+")
        var listAbilities:MutableList<Ability> = mutableListOf()
        for (i in 0 until ability.size) {
            val propiedad = ability[i].split("-")
            println("lista: $propiedad")
            listAbilities.add(Ability(propiedad[0], propiedad[1], propiedad[2]))
        }
        return listAbilities
    }

    @TypeConverter
    fun fromRoleToString(role:Role):String {
        return "${role.description}-${role.displayIcon}-${role.displayName}"
    }

    @TypeConverter
    fun fromStringToRole(role:String):Role {
        var separator = role.split("-")
        return Role(separator[0], separator[1], separator[2])
    }
}