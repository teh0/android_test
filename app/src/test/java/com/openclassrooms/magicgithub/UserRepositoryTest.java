package com.openclassrooms.magicgithub;

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator;
import com.openclassrooms.magicgithub.di.Injection;
import com.openclassrooms.magicgithub.model.User;
import com.openclassrooms.magicgithub.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Unit test, which will execute on a JVM.
 * Testing UserRepository.
 */
@RunWith(JUnit4.class)
// TODO Convertir en Kotlin
public class UserRepositoryTest {

    private UserRepository userRepository;
    /**
     * I add modifications here (new ArrayList) because every instance of repository will delegate responsibility to
     * fakeApiService which use static FakeApiServiceGenerator reference. So we need to copy value
     * of List returned by `FakeApiServiceGenerator.INSTANCE.getFAKE_USERS` and
     * `FakeApiServiceGenerator.INSTANCE.getFAKE_USERS_RANDOM` to avoid mutate these values when we
     * will do userRepository.getUsers().clear();
     */
    private List<User> FAKE_USERS = new ArrayList<User>(FakeApiServiceGenerator.INSTANCE.getFAKE_USERS());
    private List<User> FAKE_USERS_RANDOM = new ArrayList<User>(FakeApiServiceGenerator.INSTANCE.getFAKE_USERS_RANDOM());

    @Before
    public void setup() {
        userRepository = Injection.createUserRepository();
    }

    @Test
    public void getUsersWithSuccess() {
        List<User> usersActual = userRepository.getUsers();
        List<User> usersExpected = FAKE_USERS;
        assertThat(usersActual, containsInAnyOrder(usersExpected.toArray()));
    }

    @Test
    public void generateRandomUserWithSuccess() {
        userRepository.getUsers().clear();
        userRepository.generateRandomUser();
        User user = userRepository.getUsers().get(0); // Représente un des user tiré au hasard dans la liste FAKE_USERS_RANDOM
        assertEquals(1, userRepository.getUsers().size());
        assertTrue(FAKE_USERS_RANDOM.stream().map(User::getAvatarUrl).collect(Collectors.toList()).contains(user.getAvatarUrl()));
        assertTrue(FAKE_USERS_RANDOM.stream().map(User::getId).collect(Collectors.toList()).contains(user.getId()));
        assertTrue(FAKE_USERS_RANDOM.stream().map(User::getLogin).collect(Collectors.toList()).contains(user.getLogin()));
        assertFalse(FAKE_USERS.stream().map(User::getAvatarUrl).collect(Collectors.toList()).contains(user.getAvatarUrl()));
        assertFalse(FAKE_USERS.stream().map(User::getId).collect(Collectors.toList()).contains(user.getId()));
        assertFalse(FAKE_USERS.stream().map(User::getLogin).collect(Collectors.toList()).contains(user.getLogin()));
    }

    @Test
    public void deleteUserWithSuccess() {
        User userToDelete = userRepository.getUsers().get(0);
        userRepository.deleteUser(userToDelete);
        assertFalse(userRepository.getUsers().contains(userToDelete));
    }
}