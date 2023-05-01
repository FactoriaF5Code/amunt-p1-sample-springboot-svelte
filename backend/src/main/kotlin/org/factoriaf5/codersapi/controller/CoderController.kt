package org.factoriaf5.codersapi.controller

import org.factoriaf5.codersapi.repositories.Coder
import org.factoriaf5.codersapi.repositories.CoderRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class CoderController(private val coderRepo: CoderRepository) {
    @GetMapping("/api/coders")
    fun getCoders(): List<Coder> {
        return coderRepo.findAll()
    }

    @PostMapping("/api/coders")
    fun createNewCoder(@RequestBody coder: Coder): Coder? {
        return coderRepo.save(coder)
    }

    @GetMapping("/api/coders/{id}")
    fun findCoder(@PathVariable id: Long): Coder? {
        return coderRepo.findById(id).orElseThrow { CoderNotFoundException() }
    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "coder not found")
class CoderNotFoundException : RuntimeException()
