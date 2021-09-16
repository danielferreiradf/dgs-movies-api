package com.example.dgs.movie

import com.example.dgs.director.Director
import com.example.dgs.director.DirectorService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import java.time.LocalDate

@DgsComponent
class MovieMutation(
    private val movieService: MovieService,
    private val directorService: DirectorService
    ) {
    @DgsData(parentType = "Mutation", field = "newMovie")
    fun newMovie(@InputArgument("input") movieInput: MovieInput): Movie {
        return movieService.createMovie(
            Movie(
                id = null,
                name = movieInput.name,
                description = movieInput.description,
                releaseDate = LocalDate.parse(movieInput.releaseDate),
                directorId = movieInput.directorId.toLong()
            )
        )
    }
}
