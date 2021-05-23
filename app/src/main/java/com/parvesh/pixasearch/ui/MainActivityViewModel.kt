package com.parvesh.pixasearch.ui

import androidx.lifecycle.ViewModel
import com.parvesh.pixasearch.repository.PostsRepository
import com.parvesh.pixasearch.utils.Utils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val postsRepository: PostsRepository

): ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    var hideProgressBar = false
    var hideRecyclerView = true
    var hidePlaceholder = true

    init {
        hideProgressBar = true
        CoroutineScope(Dispatchers.IO).launch {

            postsRepository.getPosts(
                "apple+mango", 10)
                .subscribe(Consumer { list ->   Utils.log("View Model Log", "" + list) })

            postsRepository.updateCache("21736348-b069a7496d70ba46695b66779",
                "apple+mango",
                "photo",
                1)
        }

    }

    fun test(){
        Utils.log("View Model Log", "Working")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}