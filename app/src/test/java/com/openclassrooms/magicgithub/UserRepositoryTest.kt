package com.openclassrooms.magicgithub

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator
import com.openclassrooms.magicgithub.di.Injection.createUserRepository
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import java.util.stream.Collectors

/**
 * Unit test, which will execute on a JVM.
 * Testing UserRepository.
 */
@RunWith(JUnit4::class)
class UserRepositoryTest {
    private var userRepository: UserRepository? = null

    /**
     * I add modifications here (new ArrayList) because every instance of repository will delegate responsibility to
     * fakeApiService which use static FakeApiServiceGenerator reference. So we need to copy value
     * of List returned by `FakeApiServiceGenerator.INSTANCE.getFAKE_USERS` and
     * `FakeApiServiceGenerator.INSTANCE.getFAKE_USERS_RANDOM` to avoid mutate these values when we
     * will do userRepository.getUsers().clear();
     */
    private val FAKE_USERS: List<User> = ArrayList(FakeApiServiceGenerator.FAKE_USERS)
    private val FAKE_USERS_RANDOM: List<User> = ArrayList(FakeApiServiceGenerator.FAKE_USERS_RANDOM)
    @Before
    fun setup() {
        userRepository = createUserRepository()
    }

    @Test
    fun usersWithSuccess() {
        val usersActual: List<User> = userRepository!!.users
        val usersExpected = FAKE_USERS
        Assert.assertThat(usersActual, IsIterableContainingInAnyOrder.containsInAnyOrder<Any>(*usersExpected.toTypedArray()))
    }

    @Test
    fun generateRandomUserWithSuccess() {
        userRepository!!.users.clear()
        userRepository!!.generateRandomUser()
        val (id, login, avatarUrl) = userRepository!!.users[0] // Représente un des user tiré au hasard dans la liste FAKE_USERS_RANDOM
        Assert.assertEquals(1, userRepository!!.users.size.toLong())
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::avatarUrl).collect(Collectors.toList()).contains(avatarUrl))
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::id).collect(Collectors.toList()).contains(id))
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::login).collect(Collectors.toList()).contains(login))
        Assert.assertFalse(FAKE_USERS.stream().map(User::avatarUrl).collect(Collectors.toList()).contains(avatarUrl))
        Assert.assertFalse(FAKE_USERS.stream().map(User::id).collect(Collectors.toList()).contains(id))
        Assert.assertFalse(FAKE_USERS.stream().map(User::login).collect(Collectors.toList()).contains(login))
    }

    @Test
    fun deleteUserWithSuccess() {
        val userToDelete = userRepository!!.users[0]
        userRepository!!.deleteUser(userToDelete)
        Assert.assertFalse(userRepository!!.users.contains(userToDelete))
    }
}