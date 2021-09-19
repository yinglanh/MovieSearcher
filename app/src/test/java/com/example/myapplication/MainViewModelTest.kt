package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.network.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: Repository


    @Before
    fun setUp() {
        repository = Repository(ApiClient.apiService)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `search movies with empty searchText, returns error`() {
        try {
            viewModel.searchMovies(" ")
            Assert.fail("Should have thrown exception")
        } catch (e: Exception) {
        }
    }

    @Test
    fun `search movies with empty id, returns error`() {
        try {
            viewModel.searchMovieById("")
            Assert.fail("Should have thrown exception")
        } catch (e: Exception) {
        }
    }
}