package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User
import java.util.Random

object FakeApiServiceGenerator {
    fun generateRandomUser(): User {
        return FAKE_USERS_RANDOM.get(Random().nextInt(FAKE_USERS_RANDOM.size))
    }

    val FAKE_USERS: MutableList<User> = mutableListOf(
        User("001", "Jake", "https://source.unsplash.com/random"),
        User("002", "Paul", "https://source.unsplash.com/random"),
        User("003", "Phil", "https://source.unsplash.com/random"),
        User("004", "Guillaume", "https://source.unsplash.com/random"),
        User("005", "Francis", "https://source.unsplash.com/random"),
        User("006", "George", "https://source.unsplash.com/random"),
        User("007", "Louis", "https://source.unsplash.com/random"),
        User("008", "Mateo", "https://source.unsplash.com/random"),
        User("009", "April", "https://source.unsplash.com/random"),
        User("010", "Louise", "https://source.unsplash.com/random"),
        User("011", "Elodie", "https://source.unsplash.com/random"),
        User("012", "Helene", "https://source.unsplash.com/random"),
        User("013", "Fanny", "https://source.unsplash.com/random"),
        User("014", "Laura", "https://source.unsplash.com/random"),
        User("015", "Gertrude", "https://source.unsplash.com/random"),
        User("016", "Chloé", "https://source.unsplash.com/random"),
        User("017", "April", "https://source.unsplash.com/random"),
        User("018", "Marie", "https://source.unsplash.com/random"),
        User("019", "Henri", "https://source.unsplash.com/random"),
        User("020", "Rémi", "https://source.unsplash.com/random")
    )

    val FAKE_USERS_RANDOM = listOf(
        User("021", "Lea", "https://source.unsplash.com/random"),
        User("022", "Geoffrey", "https://source.unsplash.com/random"),
        User("023", "Simon", "https://source.unsplash.com/random"),
        User("024", "André", "https://source.unsplash.com/random"),
        User("025", "Leopold", "https://source.unsplash.com/random")
    )
}
