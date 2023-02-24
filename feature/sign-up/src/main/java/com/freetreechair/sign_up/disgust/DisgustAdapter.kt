package com.freetreechair.sign_up.disgust

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freetreechair.common.extension.setOnSingleClickListener
import com.freetreechair.common.util.ItemDiffCallback
import com.freetreechair.domain.signup.disgust.model.UIDisgust
import com.freetreechair.sign_up.databinding.ItemDisgustBinding

class DisgustAdapter(
    private val onDisgustClick: (Int) -> Unit
) : ListAdapter<UIDisgust, DisgustAdapter.DisgustViewHolder>(
    ItemDiffCallback<UIDisgust>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisgustViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = ItemDisgustBinding.inflate(inflater, parent, false)
        return DisgustViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisgustViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class DisgustViewHolder(private val binding: ItemDisgustBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(disgust: UIDisgust) {
            binding.disgust = disgust
            binding.root.setOnSingleClickListener {
                onDisgustClick(disgust.id)
                notifyItemChanged(adapterPosition)
            }
        }
    }
}
