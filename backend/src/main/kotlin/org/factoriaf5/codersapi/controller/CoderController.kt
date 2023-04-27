package org.factoriaf5.codersapi.controller

import org.factoriaf5.codersapi.repositories.Coder
import org.factoriaf5.codersapi.repositories.CoderRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RestController
class CoderController(private val coderRepo: CoderRepository) {
    @GetMapping("/api/coders")
    fun getCoders(): List<Coder> {
        return coderRepo.findAll()
    }
}