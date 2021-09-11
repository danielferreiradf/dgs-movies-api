package com.example.dgs.movie

import org.springframework.web.bind.annotation.*

@RestController
class MovieController(private val movieService: MovieService) {

    @GetMapping("/movies")
    fun getAllMovies(): List<Movie> = movieService.getAllMovies()

    @GetMapping("/movies/{id}")
    fun getMovieById(@PathVariable("id") movieId: Long): Movie =
        movieService.getMovieById(movieId)

    @PostMapping("/movies")
    fun createMovie(@RequestBody payload: Movie): Movie = movieService.createMovie(payload)

    @PutMapping("/movies/{id}")
    fun updateMovieById(@PathVariable("id") movieId: Long, @RequestBody payload: Movie): Movie =
        movieService.updateMovieById(movieId, payload)

    @DeleteMapping("/movies/{id}")
    fun deleteMovieById(@PathVariable("id") movieId: Long): Unit =
        movieService.deleteMovieById(movieId)
}