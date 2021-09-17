package com.example.dgs.movie

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MovieService(private val movieRepository: MovieRepository) {

    fun getAllMovies(): List<Movie> = movieRepository.findAll()

    fun getMovieById(movieId: Long): Movie = movieRepository.findById(movieId)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "No matching movie was found") }

    fun createMovie(movie: Movie): Movie = movieRepository.save(movie)

    fun updateMovieById(movieId: Long, movie: Movie): Movie {
        return if (movieRepository.existsById(movieId)) {
            movieRepository.save(
                Movie(
                    id = movie.id,
                    name = movie.name,
                    description = movie.description,
                    releaseDate = movie.releaseDate,
                    directorId = movie.directorId
                )
            )
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No matching movie was found")
    }

    fun deleteMovieById(movieId: Long) {
        return if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No matching movie was found")
    }
}
