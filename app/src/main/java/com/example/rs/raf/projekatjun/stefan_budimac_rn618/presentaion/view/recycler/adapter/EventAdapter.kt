package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.databinding.LayoutEventItemBinding
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.recycler.diff.EventDiffCallback
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.recycler.viewholder.EventViewHolder

class EventAdapter(private val onEventClicked: (Event) -> Unit): ListAdapter<Event, EventViewHolder>(EventDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemBinding = LayoutEventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemBinding) {
            val event = getItem(it)
            onEventClicked.invoke(event)
        }
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}