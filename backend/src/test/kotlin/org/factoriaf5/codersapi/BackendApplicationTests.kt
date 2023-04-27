package org.factoriaf5.codersapi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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

    @Test
    fun getCoders() {
        val result = restTemplate.getForEntity("/api/coders", Coder::class.java);

        assertNotNull(result)
        assertEquals(OK, result?.statusCode)
    }
}
