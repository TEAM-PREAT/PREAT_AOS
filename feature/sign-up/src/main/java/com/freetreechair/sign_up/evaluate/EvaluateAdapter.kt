package com.freetreechair.sign_up.evaluate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freetreechair.common.util.ItemDiffCallback
import com.freetreechair.domain.signup.evaluate.model.UIRestaurant
import com.freetreechair.sign_up.databinding.ItemRestaurantBinding

class EvaluateAdapter(
    private val onEvaluate: (Int, Float) -> Unit
) : ListAdapter<UIRestaurant, EvaluateAdapter.EvaluateViewHolder>(
    ItemDiffCallback<UIRestaurant>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluateViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = ItemRestaurantBinding.inflate(inflater, parent, false)
        return EvaluateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EvaluateViewHolder, position: Int) {
        holder.onBind(getItem(position), onEvaluate)
    }

    inner class EvaluateViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            restaurant: UIRestaurant,
            onEvaluate: (Int, Float) -> Unit
        ) {
            binding.restaurant = restaurant
            binding.rbRestaurant.setOnRatingBarChangeListener { _, rating, _ ->
                onEvaluate(restaurant.id, rating)
            }
        }
    }
}
