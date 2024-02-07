package com.example.apilist.model

data class Data(
    val abilities: List<Ability>,
    val background: String,
    val backgroundGradientColors: List<String>,
    val bustPortrait: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val isPlayableCharacter: Boolean,
    val role: Role,
)