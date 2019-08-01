package com.prince.syntask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.prince.syntask.R
import com.prince.syntask.model.VariantGroup
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ItemAdapter.ItemSelectedListener {
    override fun itemSelected(groupId: String?, selected: String?, position: Int): Boolean {
        return viewModel.checkExclusionsAndSelect(groupId, selected, position)
    }
    
    @Inject
    lateinit var factory: MainViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
    }

    private lateinit var mBinding: com.prince.syntask.databinding.ActivityMainBinding

    @Inject
    lateinit var mAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = mBinding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = ""
        }

        rv_variants.adapter = mAdapter

        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this

        observeData()
    }


    private fun observeData() {

        viewModel.itemLists.observe(this, Observer<List<VariantGroup>> {
            it?.let {
                mAdapter.submitList(it)
            }
        })

        viewModel.invalid.observe(this, Observer<Void> {
            Toast.makeText(baseContext, "This combination is not available", Toast.LENGTH_LONG).show()
        })

    }

}



