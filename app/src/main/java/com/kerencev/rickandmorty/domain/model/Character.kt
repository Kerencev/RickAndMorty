package com.kerencev.rickandmorty.domain.model

data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val status: String,
    val gender: String
)
