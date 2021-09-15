package com.example.dgs.director

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DirectorService(private val directorRepository: DirectorRepository) {

    fun getAllDirectors(): List<Director> = directorRepository.findAll()

    fun getDirectorById(directorId: Long): Director = directorRepository.findById(directorId)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "No matching director was found") }

    fun createDirector(director: Director): Director = directorRepository.save(director)

    fun updateDirectorById(directorId: Long, director: Director): Director {
        return if (directorRepository.existsById(directorId)) {
            directorRepository.save(
                Director(
                    id = directorId,
                    name = director.name,
                    movies = director.movies
                )
            )
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No matching director was found")
    }

    fun deleteDirectorById(directorId: Long): Unit {
        return if (directorRepository.existsById(directorId)) {
            directorRepository.deleteById(directorId)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No matching director was found")
    }
}