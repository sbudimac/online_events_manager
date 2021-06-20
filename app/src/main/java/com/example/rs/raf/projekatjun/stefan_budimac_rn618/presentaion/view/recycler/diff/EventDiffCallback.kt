package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.eventName == newItem.eventName
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.url == newItem.url
    }

}