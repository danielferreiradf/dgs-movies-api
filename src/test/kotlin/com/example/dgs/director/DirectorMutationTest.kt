package com.example.dgs.director

import com.example.demo.generated.client.NewDirectorGraphQLQuery
import com.example.demo.generated.client.NewDirectorProjectionRoot
import com.example.demo.generated.types.DirectorInput
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import io.mockk.verify
import org.assertj.core.api.Assertions.`as`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(classes = [DgsAutoConfiguration::class, DirectorFetcher::class])
internal class DirectorMutationTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var directorService: DirectorService

    private val mockedDirector = Director(id= null, name="Director test")

//    @BeforeEach
//    fun before() {
//        `when`(directorService.createDirector(mockedDirector)).thenAnswer {
//            mockedDirector
//        }
//    }

    @BeforeEach
    fun before() {
        Mockito.`when`(directorService.createDirector(mockedDirector)).thenAnswer {
            mockedDirector
        }
    }

//    @Test
//    fun newDirector() {
//        val newDirectorMutation: Director = dgsQueryExecutor.executeAndExtractJsonPath("""
//            mutation {
//                newDirector(input: {
//                    name: "Director test",
//            }) {
//              name
//                }
//            }
//        """.trimIndent(), "data.newDirector.name")
//
//        assertThat(newDirectorMutation).isEqualTo(mockedDirector)
//    }
    @Test
    fun newDirector() {

        val graphQLQueryRequest =
            GraphQLQueryRequest(
                NewDirectorGraphQLQuery.Builder()
                    .input(DirectorInput("Director Test"))
                    .build(),
                NewDirectorProjectionRoot()
                    .name()
            )

        val executionResult = dgsQueryExecutor.execute(graphQLQueryRequest.serialize())
        assertThat(executionResult.errors).isEmpty()
        assertThat(executionResult.isDataPresent).isTrue()
    }
}