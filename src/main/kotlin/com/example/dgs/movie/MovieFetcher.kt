package com.example.dgs.movie

import com.netflix.graphql.dgs.*


@DgsComponent
class MovieFetcher(val movieService: MovieService) {

    @DgsQuery
    fun movies(@InputArgument movieFilter : MovieFilter?): List<Movie> {
        val movies: List<Movie> = movieService.getAllMovies()

        return if(movieFilter != null) {
            movies.filter { it.name.lowercase().contains(movieFilter.name.lowercase()) }
        } else {
            movies
        }
    }

    @DgsQuery
    fun movie(@InputArgument id : String?): Movie? {
        return if (id != null) movieService.getMovieById(id.toLong()) else null
    }
}

