package com.example.apilist.model

import java.util.UUID

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
    val uuid: String
)