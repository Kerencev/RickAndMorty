package com.kerencev.rickandmorty.utils

interface SearchValidator {

    fun validateForCyrillic(text: String): Boolean

    class Base : SearchValidator {

        private val cyrillic = "йцукенгшщзхъфывапролджэячсмитьбю"

        override fun validateForCyrillic(text: String): Boolean {
            cyrillic.forEach { char ->
                if (text.contains(char, ignoreCase = true)) return false
            }
            return true
        }
    }
}