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
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewCoder(@RequestBody coder: Coder): Coder? {
        return coderRepo.save(coder)
    }

    @GetMapping("/api/coders/{id}")
    fun findCoder(@PathVariable id: Long): Coder? {
        return coderRepo.findById(id).orElseThrow { CoderNotFoundException() }
    }

    @DeleteMapping("/api/coders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCoder(@PathVariable id: Long): Coder? {
        val coder: Coder = coderRepo.findById(id).orElseThrow { CoderNotFoundException() }
        coderRepo.deleteById(id)
        return coder
    }

    @PutMapping("/api/coders/{id}")
    fun updateCoderById(@RequestBody coderdata: Coder, @PathVariable id: Long): Coder? {
        val coder: Coder = coderRepo.findById(id).orElseThrow { CoderNotFoundException() }
        coder.name = coderdata.name
        coder.favoriteLanguage = coderdata.favoriteLanguage
        coder.imageUrl = coderdata.imageUrl
        return coderRepo.save(coder)
    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "coder not found")
class CoderNotFoundException : RuntimeException()
