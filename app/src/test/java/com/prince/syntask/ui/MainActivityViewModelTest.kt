package com.prince.syntask.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariantsEntity
import com.prince.domain.usecases.GetVariantsUseCase
import com.prince.syntask.mapper.Mapper
import com.prince.syntask.model.VariantGroup
import com.prince.syntask.model.Variants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()


    @Mock
    lateinit var useCase: GetVariantsUseCase

    @Mock
    lateinit var mapper: Mapper<VariantsEntity, Variants>

    @Mock
    lateinit var errorObserver: Observer<Boolean>

    @Mock
    lateinit var loaderObserver: Observer<Boolean>

    @Mock
    lateinit var observer: Observer<List<VariantGroup?>>


    private lateinit var variants: VariantsEntity

    @Mock
    lateinit var variantGroups: VariantGroupEntity

    private lateinit var mainViewModel: MainActivityViewModel


    private lateinit var result: List<VariantGroupEntity>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainActivityViewModel(useCase, mapper)
        result = listOf(variantGroups, variantGroups, variantGroups, variantGroups)
        variants = VariantsEntity(listOf(emptyList()), result)
    }


    @Test
    fun fetchResult_positive() = coroutinesTestRule.testDispatcher.runBlockingTest {

        Mockito.`when`(useCase.execute()).thenReturn(variants)

        mainViewModel.loadApiData()

        mainViewModel.itemLists.observeForever {
            assertNotNull(mainViewModel.itemLists.value)
            assertEquals(4, it.size)
            assertEquals(result, it)
        }

    }

    @Test
    fun testLoading() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(useCase.execute()).thenReturn(variants)

        assertNotNull(mainViewModel.loading.value)

        mainViewModel.loadApiData()
        mainViewModel.loading.observeForever(loaderObserver)

        verify(loaderObserver).onChanged(true)
        verify(loaderObserver).onChanged(false)

    }

    @Test
    fun testError() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(useCase.execute())
            .thenAnswer { throw Exception() }

        assertNotNull(mainViewModel.error.value)

        mainViewModel.error.observeForever(errorObserver)

        mainViewModel.loadApiData()

        verify(errorObserver).onChanged(false)
        verify(errorObserver).onChanged(true)
    }
}