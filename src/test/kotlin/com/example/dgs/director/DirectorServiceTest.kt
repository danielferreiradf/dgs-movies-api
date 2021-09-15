package com.example.dgs.director

import io.mockk.*
import org.junit.jupiter.api.Test
import java.util.*

internal class DirectorServiceTest {

    private val mockedDirectors = listOf(
        Director(1, "Martin Scorsese"),
        Director(2, "Quentin Tarantino"),
        Director(3, "David Fincher")
    )

    private val directorRepository: DirectorRepository = mockk()
    private val directorService = DirectorService(directorRepository)

    @Test
    fun `should call its repository to get all directors`() {
        every { directorRepository.findAll() } returns mockedDirectors

        val directors = directorService.getAllDirectors()

        verify(exactly = 1) { directorRepository.findAll() }
    }

    @Test
    fun `should call its repository to get single director when id is received`() {
        val directorId = 1L
        val foundDirector = Director(directorId, "foundDirector")
        every { directorRepository.findById(directorId) } returns Optional.of(foundDirector)

        val director = directorService.getDirectorById(directorId)

        verify(exactly = 1) { directorRepository.findById(directorId) }
    }

    @Test
    fun `should call its repository to create new director`() {
        val newDirector = Director(123, "New director")
        every { directorRepository.save(newDirector) } returns newDirector

        val director = directorService.createDirector(newDirector)

        verify(exactly = 1) { directorRepository.save(newDirector) }
    }

    @Test
    fun `should call its repository to update a director`() {
        val directorId = 1L
        val updatedDirector = Director(directorId, "Updated director name")

        every { directorRepository.existsById(directorId) } returns true
        every { directorRepository.save(updatedDirector) } returns updatedDirector

        val director = directorService.updateDirectorById(directorId, updatedDirector)

        verify(exactly = 1) { directorRepository.save(updatedDirector) }
    }

    @Test
    fun `should call its repository to delete a director`() {
        val directorId = 1L

        every { directorRepository.existsById(directorId) } returns true
        every { directorRepository.deleteById(directorId) } just Runs

        val director = directorService.deleteDirectorById(directorId)

        verify(exactly = 1) { directorRepository.deleteById(directorId) }
    }
}

