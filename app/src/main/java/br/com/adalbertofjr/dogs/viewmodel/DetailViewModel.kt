package br.com.adalbertofjr.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.adalbertofjr.dogs.model.DogBreed

class DetailViewModel : ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog =
            DogBreed("1", "Corgi", "15 years", "breadGroup", "bredFor", "temperament", "image")
        dogLiveData.value = dog
    }
}