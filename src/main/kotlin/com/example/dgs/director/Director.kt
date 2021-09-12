package com.example.dgs.director

import com.example.dgs.movie.Movie
import javax.persistence.*

@Entity
@Table(name = "director")
data class Director (
    @Id
    @GeneratedValue
    val id: Long?,
    @Column(name = "name", unique = true, nullable = false)
    val name: String,
    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    val movies: List<Movie> = emptyList()
)