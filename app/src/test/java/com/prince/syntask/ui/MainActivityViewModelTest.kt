package com.prince.syntask.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prince.syntask.data.repository.MainRepository
import com.prince.domain.entities.VariantGroupEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var repository: MainRepository

    @Mock
    lateinit var observer: androidx.lifecycle.Observer<List<VariantGroupEntity>>

    @Mock
    lateinit var variants: VariantGroupEntity

    lateinit var mainViewModel: MainActivityViewModel


    lateinit var result: List<VariantGroupEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainActivityViewModel(repository)
        result = listOf(variants, variants, variants, variants)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchResult_positive() = runBlocking {
        Mockito.`when`(repository.fetchResults()).thenReturn(result)


        mainViewModel.itemLists.observeForever(observer)

        mainViewModel.loadApiData()

        org.junit.Assert.assertNotNull(mainViewModel.itemLists.value)
        org.junit.Assert.assertEquals(mainViewModel.itemLists.value?.size, 4)
    }

    @Test
    fun testLoading() = runBlocking {
        Mockito.`when`(repository.fetchResults()).thenReturn(result)

        mainViewModel.loadApiData()
        assertEquals(mainViewModel.loading.value, false)
    }

    @Test
    fun testError() = runBlocking {

        val exception = Exception()

        Mockito.`when`(repository.fetchResults())
            .thenThrow(exception)

        mainViewModel.loadApiData()
        org.junit.Assert.assertEquals(mainViewModel.error.value, true)
    }
}