package com.example.dgs.director

import com.netflix.graphql.dgs.*


@DgsComponent
class DirectorFetcher(val directorService: DirectorService) {

    @DgsQuery
    fun directors(): List<Director> {
        return directorService.getAllDirectors()
    }

    @DgsQuery
    fun director(@InputArgument id : String?): Director? {
        return if (id != null) directorService.getDirectorById(id.toLong()) else null
    }
}

