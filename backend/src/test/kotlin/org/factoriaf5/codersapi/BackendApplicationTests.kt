package org.factoriaf5.codersapi

import org.factoriaf5.codersapi.repositories.Coder
import org.factoriaf5.codersapi.repositories.CoderRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus.OK

@SpringBootTest(
        classes = [BackendApplication::class],
        webEnvironment = RANDOM_PORT
)
class BackendApplicationTests {

    @Autowired //injecta una dependecia
    lateinit var restTemplate: TestRestTemplate


    @Autowired
    lateinit var repository : CoderRepository

    @BeforeEach //ejecuta antes de cada test
    fun setUp() {
        repository.deleteAll()
    }

    @Test
    fun getCoders() {
        //given
        val coder = Coder(name = "Pepe", favoriteLanguage ="PHP", imageUrl ="example.jpg")
        repository.save(coder)

        // when
        val response = restTemplate.getForEntity("/api/coders", Array<Coder>::class.java);

        // then
        assertEquals(OK, response.statusCode)
        assertEquals(response.body?.size, 1)
        assertEquals(response.body?.get(0), coder)
    }
}
