package org.factoriaf5.codersapi.repositories

import jakarta.persistence.*

@Entity
data class Coder(
        var name: String,
        var favoriteLanguage: String,
        var imageUrl: String,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null
)
