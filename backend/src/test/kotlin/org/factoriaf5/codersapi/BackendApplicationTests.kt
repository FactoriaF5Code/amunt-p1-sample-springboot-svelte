package org.factoriaf5.codersapi


import com.fasterxml.jackson.databind.ObjectMapper
import org.factoriaf5.codersapi.controller.CoderNotFoundException

import org.factoriaf5.codersapi.repositories.Coder
import org.factoriaf5.codersapi.repositories.CoderRepository
import org.hamcrest.MatcherAssert

import org.hamcrest.Matchers.*
import org.hibernate.annotations.NotFound
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.*
import org.springframework.test.util.AssertionErrors.assertFalse
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@SpringBootTest(
        classes = [BackendApplication::class],
        webEnvironment = RANDOM_PORT
)
class BackendApplicationTests {


    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired //injecta una dependecia
    lateinit var restTemplate: TestRestTemplate


    @Autowired
    lateinit var repository: CoderRepository

    @BeforeEach //ejecuta antes de cada test
    fun setUp() {
        repository.deleteAll()
    }


    @Test
    fun getCoders() {
        //given
        val coder = Coder(name = "Pepe", favoriteLanguage = "PHP", imageUrl = "example.jpg")
        repository.save(coder)

        // when
        val response = restTemplate.getForEntity("/api/coders", Array<Coder>::class.java);

        // then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body?.size, 1)
        assertEquals(response.body?.get(0), coder)
    }

    @Test
    fun createNewCoder() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestBody = Coder("Gabi", "Kotlin", "example2.jpg")
        val request = HttpEntity(requestBody, headers)
        val response = restTemplate.postForEntity("/api/coders", request, Coder::class.java)
        val id = response.getBody()!!.id!!
        assertEquals(HttpStatus.CREATED, response.statusCode)
        val coder = repository.findById(id).get()
        assertEquals(coder.name, "Gabi")
        assertEquals(coder.favoriteLanguage, "Kotlin")
        assertEquals(coder.imageUrl, "example2.jpg")
    }

    @Test
    fun getCoderById() {

        val coder = Coder(name = "Gabi", favoriteLanguage = "Kotlin", imageUrl = "example2.jpg")
        repository.save(coder)
        val coderId = coder.id

        val response = restTemplate.getForEntity("/api/coders/{id}", Coder::class.java, coderId)
        val responseBody = response.getBody()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(coder.id, responseBody?.id)
        assertEquals(coder.name, responseBody?.name)
        assertEquals(coder.favoriteLanguage, responseBody?.favoriteLanguage)
        assertEquals(coder.imageUrl, responseBody?.imageUrl)

    }

    @Test
    fun getCoderByIdNotFound(){
        val id = 999999
        val response = restTemplate.getForEntity("/api/coders/{id}", CoderNotFoundException::class.java, id)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun deleteCoder(){
        val coder = Coder(name = "Gabi", favoriteLanguage = "Kotlin", imageUrl = "example2.jpg")
        repository.save(coder)
        val coderId = coder.id!!
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity<Coder>(headers)
        val response = restTemplate.exchange("/api/coders/{id}", HttpMethod.DELETE, request, Coder::class.java, coderId)
        val deleteCoder = repository.findById(coderId)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        assertTrue(deleteCoder.isEmpty())
    }

}


