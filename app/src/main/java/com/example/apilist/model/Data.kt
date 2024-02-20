package com.example.apilist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "AgentEntity")
data class Data(
    val abilities: List<Ability>,
    val background: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val isPlayableCharacter: Boolean,
    val role: Role,
    @PrimaryKey
    val uuid: String
)