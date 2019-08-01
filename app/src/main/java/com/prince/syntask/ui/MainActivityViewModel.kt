package com.prince.syntask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prince.domain.entities.VariantsEntity
import com.prince.domain.usecases.GetVariantsUseCase
import com.prince.syntask.mapper.Mapper
import com.prince.syntask.model.Exclusions
import com.prince.syntask.model.VariantGroup
import com.prince.syntask.model.Variants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class MainActivityViewModel(
    private val useCase: GetVariantsUseCase,
    private val mapper: Mapper<VariantsEntity, Variants>
) : ViewModel() {
    private val exclusionMap1 = hashMapOf<String, HashMap<String, ArrayList<Exclusions>>>()
    private val selectedMap = hashMapOf<String, String>()

    private val _itemLists = MutableLiveData<List<VariantGroup>>()
    private val _invalid = MutableLiveData<Void>()

    private var items = ArrayList<VariantGroup>();

    private val _loading = MutableLiveData<Boolean>()

    private val _error = MutableLiveData<Boolean>()

    val itemLists: LiveData<List<VariantGroup>>
        get() = _itemLists

    val loading: LiveData<Boolean>
        get() = _loading

    val error: LiveData<Boolean>
        get() = _error

    val invalid: LiveData<Void>
        get() = _invalid


    init {
        loadApiData()
    }


    /**
     * Method to  initiate the apis
     */
    fun loadApiData() {

        viewModelScope.launch {
            try {
                _error.value = false
                _loading.value = true
                val result = withContext(Dispatchers.IO) {
                    useCase.execute()
                }
                transformData(mapper.from(result))
            } catch (t: Throwable) {
                Timber.d("Error  ${t.message}")
                _error.value = true
            } finally {
                _loading.value = false
            }
        }
    }


    /**
     * Method to transform the input data to include the excluded items
     */
    private fun transformData(result: Variants) {

        val excluded = result.excludeList
        items = result.variantGroups as ArrayList<VariantGroup>

        for (outerItem in excluded) {
            var map = HashMap<String, ArrayList<Exclusions>>()
            val item1 = outerItem[0]
            val item2 = outerItem[1]
            if (exclusionMap1[item1.groupId] == null) {
                map[item1.variationId] = arrayListOf(item2)
                exclusionMap1[item1.groupId] = map
            } else {
                if (exclusionMap1[item1.groupId]?.get(item1.variationId) == null) {
                    map[item1.variationId] = arrayListOf(item2)
                    exclusionMap1[item1.groupId] = map
                } else {
                    val list: ArrayList<Exclusions> = exclusionMap1[item1.groupId]?.get(item1.variationId)!!
                    list.add(item2)
                    exclusionMap1[item1.groupId]?.set(item1.variationId, list)
                }
            }

            map = HashMap()
            if (exclusionMap1[item2.groupId] == null) {
                map[item2.variationId] = arrayListOf(item1)
                exclusionMap1[item2.groupId] = map
            } else {
                if (exclusionMap1[item2.groupId]?.get(item2.variationId) == null) {
                    map[item2.variationId] = arrayListOf(item1)
                    exclusionMap1[item2.groupId] = map
                } else {
                    val list: ArrayList<Exclusions> = exclusionMap1[item2.groupId]?.get(item2.variationId)!!
                    list.add(item1)
                    exclusionMap1[item2.groupId]?.set(item2.variationId, list)
                }
            }

        }
        _itemLists.value = items

    }


    /**
     * Method to check if a selection is valid one or not
     */
    fun checkExclusionsAndSelect(groupId: String?, variationId: String?, position: Int): Boolean {
        val list = exclusionMap1[groupId]?.get(variationId)

        if (list != null) {
            for (exclusion in list) {
                if (selectedMap[exclusion.groupId] == exclusion.variationId) {
                    _invalid.value = null
                    return false
                }
            }
        }

        items[position].selected = variationId ?: ""
        _itemLists.value = items
        groupId?.let {
            if (variationId != null) {
                selectedMap[it] = variationId
            }
        }
        return true
    }

}