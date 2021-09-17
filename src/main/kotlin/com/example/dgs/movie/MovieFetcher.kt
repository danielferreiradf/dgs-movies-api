package com.example.dgs.movie

import com.example.dgs.director.Director
import com.netflix.graphql.dgs.*
import graphql.schema.DataFetchingEnvironment
import org.dataloader.DataLoader
import java.util.concurrent.CompletableFuture


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

    @DgsData(parentType = "Movie", field = "director")
    fun movieDirector(dfe: DataFetchingEnvironment): CompletableFuture<List<Director>>? {
        val dataLoader: DataLoader<Long, List<Director>> = dfe.getDataLoader<Long, List<Director>>("directors")
        val movie: Movie = dfe.getSource()
        return dataLoader.load(movie.directorId)
    }

}

