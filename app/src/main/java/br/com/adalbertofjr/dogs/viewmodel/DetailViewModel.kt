package br.com.adalbertofjr.dogs.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import br.com.adalbertofjr.dogs.model.DogBreed
import br.com.adalbertofjr.dogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(dogUuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(dogUuid)
            dogRetrivied(dog)
            Toast.makeText(getApplication(), "Dog retrivied from database", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun dogRetrivied(dog: DogBreed) {
        dogLiveData.value = dog
    }
}