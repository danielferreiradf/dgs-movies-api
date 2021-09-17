package com.example.dgs.director

import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.BatchLoader
import org.dataloader.MappedBatchLoader
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.function.Supplier

@DgsDataLoader(name = "directors")
class DirectorsDataLoader : MappedBatchLoader<Long, Director> {
    @Autowired
    private lateinit var directorRepository: DirectorRepository

    override fun load(keys: Set<Long?>): CompletionStage<Map<Long?, Director>> {
        return CompletableFuture.supplyAsync(Supplier {
            directorRepository.findAllById(keys).map { it.id to it }.toMap()
        })
    }
}
