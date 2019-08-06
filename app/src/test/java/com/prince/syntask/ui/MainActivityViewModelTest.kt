package com.prince.syntask.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariantsEntity
import com.prince.domain.usecases.GetVariantsUseCase
import com.prince.syntask.mapper.Mapper
import com.prince.syntask.model.Variants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
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
    lateinit var useCase: GetVariantsUseCase

    @Mock
    lateinit var mapper: Mapper<VariantsEntity, Variants>

    private lateinit var variants: VariantsEntity

    @Mock
    lateinit var variantGroups: VariantGroupEntity

    private lateinit var mainViewModel: MainActivityViewModel


    private lateinit var result: List<VariantGroupEntity>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel = MainActivityViewModel(useCase, mapper)
        result = listOf(variantGroups, variantGroups, variantGroups, variantGroups)
        variants = VariantsEntity(listOf(emptyList()), result)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchResult_positive() = runBlocking(Dispatchers.Unconfined) {

        Mockito.`when`(useCase.execute()).thenReturn(variants)

        mainViewModel.loadApiData()

        mainViewModel.itemLists.observeForever {
            assertNotNull(mainViewModel.itemLists.value)
            assertEquals(4, it.size)
            assertEquals(result, it)
        }

    }

    @Test
    fun testLoading() = runBlocking (Dispatchers.Unconfined){
        Mockito.`when`(useCase.execute()).thenReturn(variants)

        assertNotNull(mainViewModel.loading.value)

        mainViewModel.loadApiData()
        mainViewModel.loading.observeForever {}

        assertEquals(mainViewModel.loading.value, false)


    }

    @Test
    fun testError() {
        runBlocking(Dispatchers.Unconfined) {
            Mockito.`when`(useCase.execute())
                .thenAnswer { throw Exception() }

            assertNotNull(mainViewModel.error.value)
            mainViewModel.loadApiData()

            mainViewModel.error.observeForever {
                assertTrue(mainViewModel.error.value == true)
            }

        }

    }

}