package com.example.dgs.movie

import com.example.dgs.director.Director
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "movie")
data class Movie (
    @Id
    @GeneratedValue
    val id: Long?,
    @Column(name = "name", unique = true, nullable = false)
    val name: String,
    @Column(name = "description", nullable = false)
    val description: String,
    @Column(name = "release_date", nullable = false)
    val releaseDate: LocalDate,
    @ManyToOne(fetch = FetchType.LAZY)
    val director: Director? = null,
    @Column(name = "director_id", insertable = false, updatable = false)
    val directorId: Long
)
