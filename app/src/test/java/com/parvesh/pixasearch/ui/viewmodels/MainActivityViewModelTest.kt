package com.parvesh.pixasearch.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.parvesh.pixasearch.cache.models.PostDomainEntityMapper
import com.parvesh.pixasearch.repositories.PostsRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val entityMapper: PostDomainEntityMapper = PostDomainEntityMapper()

    @Mock
    private lateinit var postsRepository: PostsRepository

    @Before
    fun setup() {
        mainActivityViewModel = MainActivityViewModel(postsRepository, entityMapper)
    }

    /*
    This validation protects against path traversal attacks and also other injection attacks
     */
    @Test
    fun `invalid search term, returns false`() {
        assertThat(mainActivityViewModel.validateSearchTerm("apple/../../etc/hosts")).isFalse()
    }

    @Test
    fun `valid search term, returns true`() {
        assertThat(mainActivityViewModel.validateSearchTerm("fruits")).isTrue()
    }
}