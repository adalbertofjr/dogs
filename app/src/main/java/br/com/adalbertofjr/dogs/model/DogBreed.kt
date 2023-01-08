package br.com.adalbertofjr.dogs.model

data class DogBreed(
    val breedId: String = "0",
    val dogBreed: String?,
    val lifeSpan: String?,
    val breedGroup: String?,
    val bredFor: String?,
    val temperament: String?,
    val imageUrl: String?
)
