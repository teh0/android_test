package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(private val apiService: ApiService) {

    val users: List<User>
        get() {
            return this.apiService.getUsers()
        }

    fun generateRandomUser() {
        this.apiService.generateRandomUser()
    }

    fun deleteUser(user: User?) {
        if (user != null) {
            this.apiService.deleteUser(user)
        }
    }
}
