package com.prince.syntask.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prince.syntask.databinding.LayoutItemListBinding
import com.prince.syntask.databinding.LayoutItemVariantsBinding
import com.prince.syntask.model.VariantGroup

class ItemAdapter(context: Context) :
    ListAdapter<VariantGroup, ItemAdapter.ItemViewHolder>(DIFF_UTIL) {


    private val itemSelected: ItemSelectedListener = context as ItemSelectedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent, itemSelected)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            holder.bind(it)
        }
    }

    class ItemViewHolder(
        @NonNull private val binding: LayoutItemListBinding,
        itemSelected: ItemSelectedListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private var variantModel: VariantGroup? = null

        init {
            binding.rdGroup.setOnCheckedChangeListener { group, checkedId ->
                var previousId: RadioButton? = null
                if (variantModel?.selected != "") {
                    previousId = variantModel?.selected?.toInt()?.let { group.findViewById<RadioButton>(it) }
                }
                val radioButton = group.findViewById<RadioButton>(checkedId)
                if (radioButton != null) {
                    radioButton.isChecked =
                        itemSelected.itemSelected(variantModel?.groupId, checkedId.toString(), adapterPosition)
                    if (!radioButton.isChecked && previousId != null) previousId.isChecked = true
                }
            }
        }

        fun bind(model: VariantGroup) {
            variantModel = model
            binding.model = model
            binding.executePendingBindings()
            binding.rdGroup.removeAllViews()
            binding.layoutDetails.removeAllViews()

            for (variation in model.variations) {
                val button = RadioButton(binding.rdGroup.context)
                button.id = variation.id.toInt()
                button.text = variation.name
                binding.rdGroup.addView(button)
                val layout =
                    LayoutItemVariantsBinding.inflate(
                        LayoutInflater.from(binding.layoutDetails.context),
                        binding.layoutDetails,
                        true
                    )
                layout.model = variation
            }

            if (model.selected == "") {
                binding.rdGroup.clearCheck()
            } else {
                val count = binding.rdGroup.childCount
                for (i in 0 until count) {
                    val v = binding.rdGroup.getChildAt(i)
                    if (v is RadioButton) {
                        v.isChecked = v.id == model.selected.toInt()
                    }
                }
            }
        }


        companion object {
            fun from(parent: ViewGroup, itemSelected: ItemSelectedListener): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemListBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(binding, itemSelected)
            }
        }
    }


    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<VariantGroup>() {
            override fun areItemsTheSame(oldItem: VariantGroup, newItem: VariantGroup): Boolean {
                return oldItem.groupId == newItem.groupId
            }

            override fun areContentsTheSame(oldItem: VariantGroup, newItem: VariantGroup): Boolean {
                return newItem == oldItem
            }
        }
    }


    interface ItemSelectedListener {
        fun itemSelected(groupId: String?, selected: String?, position: Int): Boolean
    }
}