package org.factoriaf5.codersapi.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoderRepository : JpaRepository<Coder,Long>