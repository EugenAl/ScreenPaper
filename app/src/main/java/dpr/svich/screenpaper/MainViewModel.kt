package dpr.svich.screenpaper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dpr.svich.screenpaper.model.UnsplashModel
import dpr.svich.screenpaper.repository.PhotoRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = PhotoRepository()

    var photos: MutableLiveData<List<UnsplashModel>> =
        MutableLiveData<List<UnsplashModel>>()

    fun loadPhoto(category: String){
        viewModelScope.launch {
            val photosList = repository.loadPhoto(category)
            photos.value = photosList
        }
    }
}