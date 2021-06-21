package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.databinding.ActivityEventListBinding
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.contract.EventContract
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.recycler.adapter.EventAdapter
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.state.EventsState
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class EventListActivity : AppCompatActivity() {
    private val eventViewModel: EventContract.ViewModel by viewModel<EventViewModel>()
    private lateinit var binding: ActivityEventListBinding
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.eventsRv.layoutManager = LinearLayoutManager(this)
        adapter = EventAdapter(eventViewModel) {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra("EVENT_NAME", it.eventName)
            intent.putExtra("DESCRIPTION", it.description)
            intent.putExtra("DATE", it.date)
            intent.putExtra("TIME", it.time)
            intent.putExtra("SIZE", it.size)
            intent.putExtra("URL", it.url)
            intent.type ="text/plain"
            val sendIntent = Intent.createChooser(intent, "Choose a program")
            startActivity(sendIntent)
        }
        binding.eventsRv.adapter = adapter
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initObservers() {
        eventViewModel.eventState.observe(this, {
            Timber.e(it.toString())
            renderState(it)
        })
        eventViewModel.getAllEvents()
    }

    private fun renderState(state: EventsState) {
        when (state) {
            is EventsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.events)
            }
            is EventsState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is EventsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is EventsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.eventsTitle.isVisible = !loading
        binding.eventsRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }
}