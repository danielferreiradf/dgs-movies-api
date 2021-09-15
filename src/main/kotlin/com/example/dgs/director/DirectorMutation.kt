package com.example.dgs.director


import com.example.demo.generated.types.DirectorInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class DirectorMutation(val directorService: DirectorService) {
    @DgsData(parentType = "Mutation", field = "newDirector")
    fun newDirector(@InputArgument("input") directorInput: DirectorInput): Director {
        return directorService.createDirector(
            Director(null, name = directorInput.name)
        )
    }
}