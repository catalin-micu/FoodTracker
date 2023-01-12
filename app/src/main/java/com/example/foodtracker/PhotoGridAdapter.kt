package com.example.foodtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtracker.network.RecipeDataClass
import com.example.foodtracker.databinding.GridViewItemBinding


class PhotoGridAdapter( private val onClickListener: OnClickListener ) :
    ListAdapter<RecipeDataClass,
            PhotoGridAdapter.RecipeViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.RecipeViewHolder {
        return RecipeViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipe)
        }
        holder.bind(recipe)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<RecipeDataClass>() {

        override fun areItemsTheSame(oldItem: RecipeDataClass, newItem: RecipeDataClass): Boolean {
            return oldItem.id === newItem.id
        }


        override fun areContentsTheSame(oldItem: RecipeDataClass, newItem: RecipeDataClass): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class RecipeViewHolder(private var binding:
                                 GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeDataClass) {
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (recipe:RecipeDataClass) -> Unit) {
        fun onClick(recipe:RecipeDataClass) = clickListener(recipe)
    }
}