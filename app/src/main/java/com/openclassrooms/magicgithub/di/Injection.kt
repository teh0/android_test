package com.openclassrooms.magicgithub.di

import com.openclassrooms.magicgithub.api.FakeApiService
import com.openclassrooms.magicgithub.repository.UserRepository

object Injection {
    @JvmStatic
    fun createUserRepository(): UserRepository {
        return UserRepository(FakeApiService())
    }
}