package com.example.dgs.movie

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "movie")
data class Movie (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "name", unique = true, nullable = false)
    val name: String,
    @Column(name = "description", nullable = false)
    val description: String,
    @Column(name = "release_date", nullable = false)
    val releaseDate: LocalDate
)