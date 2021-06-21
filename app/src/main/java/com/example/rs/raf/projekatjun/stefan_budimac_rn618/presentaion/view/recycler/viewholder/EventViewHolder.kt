package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.recycler.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.databinding.LayoutEventItemBinding
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.contract.EventContract
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.viewmodel.EventViewModel

class EventViewHolder(private val itemBinding: LayoutEventItemBinding, private val eventViewModel: EventContract.ViewModel, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
    init {
        itemBinding.root.setOnClickListener {
            onItemClicked.invoke(absoluteAdapterPosition)
        }
    }

    fun bind(event: Event) {
        itemBinding.eName.text = event.eventName
        itemBinding.eDescription.text = event.description
        itemBinding.eTime.text = event.time
        itemBinding.eUrl.text = event.url

        if (event.size == "High") {
            itemBinding.rvItem.setBackgroundColor(Color.RED)
        } else if (event.size == "Medium") {
            itemBinding.rvItem.setBackgroundColor(Color.YELLOW)
        } else {
            itemBinding.rvItem.setBackgroundColor(Color.GREEN)
        }

        itemBinding.deleteEvent.setOnClickListener {
            eventViewModel.deleteEvent(itemBinding.eName.text.toString())
        }
    }
}