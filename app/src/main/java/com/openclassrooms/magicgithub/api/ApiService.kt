package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User

interface ApiService {
    fun getUsers(): List<User>
    fun generateRandomUser()
    fun deleteUser(user: User)
}
