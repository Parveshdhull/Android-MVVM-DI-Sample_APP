package com.parvesh.pixasearch.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parvesh.pixasearch.cache.models.PostDomainEntityMapper
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import com.parvesh.pixasearch.domain.models.Post
import com.parvesh.pixasearch.repositories.PostsRepository
import com.parvesh.pixasearch.utils.Utils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val entityMapper: PostDomainEntityMapper
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var mutableLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    private var dataset: List<PostEntity> = ArrayList()
    private var totalHits: Int = 0
    private var searchTerm: String = "searchTerm"
    private var page: Int = 1

    var hideProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    var hideRecyclerView: MutableLiveData<Boolean> = MutableLiveData(true)
    var hidePlaceholder: MutableLiveData<Boolean> = MutableLiveData(true)

    var errorToast = MutableLiveData<String?>()

    private var updatingPosts: Boolean = true
    private var updatingSearchEntity: Boolean = true

    init {
        Utils.log("ViewModel", "Created")
        showProgressbar()
    }

    private fun validateSearchTerm(searchTerm: String): Boolean {
        if (Utils.containsSpecialCharacters(searchTerm)) {
            errorToast.value = "Special Characters Not Allowed"
            return false
        } else if (searchTerm.length > 99) {
            errorToast.value = "Query Length exceeded"
            return false
        }
        return true
    }

    fun search(searchTerm: String) {
        if (validateSearchTerm(searchTerm)) {
            disposables.clear()
            showProgressbar()
            totalHits = 0
            page = 1
            this.searchTerm = searchTerm
            getDataFromRepository(Utils.urlEncodeString(input = searchTerm), 1)
        }
    }

    private fun getDataFromRepository(searchTerm: String, page: Int) {
        updatingPosts = true
        updatingSearchEntity = true
        CoroutineScope(Dispatchers.IO).launch {

            disposables.add(postsRepository.getPosts(searchTerm)
                .subscribe { list -> postObserverFired(list ?: ArrayList()) })

            disposables.add(postsRepository.getSearchEntity(searchTerm)
                .subscribe { searchEntity -> searchEntityObserverFired(searchEntity) })

            postsRepository.updateCache(searchTerm, page)
        }
    }

    private fun postObserverFired(list: List<PostEntity>) {
        dataset = list
        mutableLiveData.postValue(entityMapper.toDomainList(list))
        updatingPosts = false
        if (!updatingSearchEntity) {
            updateView()
        }
    }

    private fun searchEntityObserverFired(searchEntity: SearchEntity) {
        totalHits = searchEntity.totalHits
        updatingSearchEntity = false
        if (!updatingPosts) {
            updateView()
        }
    }


    private fun updateView() {
        if (dataset.isEmpty() && totalHits == 0) {
            showPlaceholder()
        } else if (dataset.isNotEmpty()) {
            showRecyclerView()
        }
    }


    fun downloadMorePosts() {
        var currentSize = page * 20
        if (currentSize < totalHits) {
            disposables.clear()
            getDataFromRepository(Utils.urlEncodeString(input = searchTerm), ++page)
        } else {
            errorToast.postValue("No More Results")
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun showProgressbar() {
        hideProgressBar.postValue(false)
        hideRecyclerView.postValue(true)
        hidePlaceholder.postValue(true)
    }

    private fun showRecyclerView() {
        hideProgressBar.postValue(true)
        hideRecyclerView.postValue(false)
        hidePlaceholder.postValue(true)
    }

    private fun showPlaceholder() {
        hideProgressBar.postValue(true)
        hideRecyclerView.postValue(true)
        hidePlaceholder.postValue(false)
    }

    fun getPosts(): LiveData<List<Post>> {
        return mutableLiveData
    }

}