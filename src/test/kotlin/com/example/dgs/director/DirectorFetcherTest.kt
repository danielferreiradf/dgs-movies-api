package com.example.dgs.director

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(classes = [DgsAutoConfiguration::class, DirectorFetcher::class])
class DirectorFetcherTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var directorService: DirectorService

    @BeforeEach
    fun before() {
        Mockito.`when`(directorService.getAllDirectors()).thenAnswer {
            listOf(Director(1, "director 1"), Director(2, "director 2"))
        }
        Mockito.`when`(directorService.getDirectorById(1)).thenAnswer {
            Director(1, "director 1")
        }
    }

    @Test
    fun directors() {
        val names: List<String> = dgsQueryExecutor.executeAndExtractJsonPath("""
            {
                directors {
                    name
                }
            }
        """.trimIndent(), "data.directors[*].name")

        assertThat(names).contains("director 1", "director 2")
    }

    @Test
    fun director() {
        val name: String = dgsQueryExecutor.executeAndExtractJsonPath("""
            {
                director(id: 1) {
                    name
                }
            }
        """.trimIndent(), "data.director.name")

        assertThat(name).contains("director 1")
    }
}