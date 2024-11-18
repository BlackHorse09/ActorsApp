package com.sahil.actorsapp.repository

import com.sahil.actorsapp.retrofit.Character
import com.sahil.actorsapp.retrofit.CharacterApi

class CharacterRepo(val characterApi: CharacterApi) {
    suspend fun getCharacters() : List<Character> {
        return characterApi.getCharacters()
    }
}