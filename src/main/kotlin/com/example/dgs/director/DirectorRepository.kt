package com.example.dgs.director

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DirectorRepository : JpaRepository<Director, Long>