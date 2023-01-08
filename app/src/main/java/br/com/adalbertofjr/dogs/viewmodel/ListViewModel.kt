package br.com.adalbertofjr.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.adalbertofjr.dogs.model.DogBreed

class ListViewModel : ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {

        val dog1 =
            DogBreed("1", "Corgi", "15 years", "breadGroup", "bredFor", "temperament", "image")
        val dog2 = DogBreed("2", "Pug", "15 years", "breadGroup", "bredFor", "temperament", "image")
        val dog3 =
            DogBreed("3", "Labrador", "15 years", "breadGroup", "bredFor", "temperament", "image")
        val dogList = arrayListOf<DogBreed>(dog1, dog2, dog3)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}