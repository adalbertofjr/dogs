package br.com.adalbertofjr.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.adalbertofjr.dogs.model.DogBreed
import br.com.adalbertofjr.dogs.model.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(dogsList: List<DogBreed>) {
                        loading.value = false
                        dogsLoadError.value = false
                        dogs.value = dogsList
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        dogsLoadError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }
}