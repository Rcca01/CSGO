package com.example.csgo.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.json.*

data class Players (
    @SerializedName("current_team")
    val currentTeam: CurrentTeam,

    @SerializedName("current_videogame")
    val currentVideogame: CurrentVideogame,

    @SerializedName("first_name")
    val firstName: String,

    val hometown: String? = null,
    val id: Long,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    val name: String,
    val nationality: String? = null,
    val role: JsonObject? = null,
    val slug: String
)

data class CurrentTeam (
    val acronym: String? = null,
    val id: Long,

    @SerializedName("image_url")
    val imageURL: String,

    val location: String,

    @SerializedName("modified_at")
    val modifiedAt: String,

    val name: String,
    val slug: String
)

data class CurrentVideogame (
    val id: Long,
    val name: String,
    val slug: String
)

