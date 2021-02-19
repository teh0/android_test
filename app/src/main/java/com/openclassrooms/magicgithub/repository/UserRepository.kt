package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(private val apiService: ApiService) {

    val users: List<User>
        get() {
            TODO("A modifier")
        }

    fun generateRandomUser() {
        TODO("A modifier")
    }

    fun deleteUser(user: User?) {
        TODO("A modifier")
    }
}
