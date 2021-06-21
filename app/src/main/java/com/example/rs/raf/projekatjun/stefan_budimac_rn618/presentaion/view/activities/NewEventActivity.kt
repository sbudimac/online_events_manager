package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.R
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.databinding.ActivityNewEventBinding
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.contract.EventContract
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewEventActivity : AppCompatActivity() {
    private val eventViewModel: EventContract.ViewModel by viewModel<EventViewModel>()
    private lateinit var binding: ActivityNewEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
        initListeners()
    }

    private fun initUi() {
        val spinner: Spinner = binding.sizeSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.priorities,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }

        val autocompleteTv: AutoCompleteTextView = binding.etCity
        ArrayAdapter.createFromResource(
            this,
            R.array.cities,
            android.R.layout.simple_dropdown_item_1line
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            autocompleteTv.setAdapter(arrayAdapter)
        }
    }

    private fun initListeners() {
        val btnCityTime = binding.btnCheckTime

        btnCityTime.setOnClickListener {
            if (binding.etCity.text.toString() == "") {
                Toast.makeText(this, "Enter a city.", Toast.LENGTH_SHORT).show()
            } else {
                eventViewModel.fetchCityTime(binding.etCity.text.toString())
                eventViewModel.cityTime.observe(this, {
                    binding.btnCheckTime.text = it.dateTime
                })
            }
        }

        val btnDate = binding.btnSetDate
        val btnTime = binding.btnSetTime

        val myCalendar = Calendar.getInstance()
        val date =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabelDate(btnDate, myCalendar)
            }
        val time =
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                myCalendar[Calendar.HOUR] = selectedHour
                myCalendar[Calendar.MINUTE] = selectedMinute
                updateLabelTime(btnTime, myCalendar)
            }

        btnDate.setOnClickListener {
            DatePickerDialog(
                this@NewEventActivity, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        btnTime.setOnClickListener {
            TimePickerDialog(
                this@NewEventActivity, time, myCalendar[Calendar.HOUR_OF_DAY], myCalendar[Calendar.MINUTE], true
            ).show()
        }

        val formatter: DateFormat = SimpleDateFormat("MM/dd/yy")
        binding.btnSaveEvent.setOnClickListener {
            if (binding.eventName.text.toString() == "") {
                Toast.makeText(this, "Enter event name.", Toast.LENGTH_SHORT).show()
            } else if (binding.description.text.toString() == "") {
                Toast.makeText(this, "Enter event description.", Toast.LENGTH_SHORT).show()
            } else if (binding.btnSetDate.text == "SET DATE") {
                Toast.makeText(this, "Set event date.", Toast.LENGTH_SHORT).show()
            } else if (binding.btnSetTime.text == "SET TIME") {
                Toast.makeText(this, "Set event time.", Toast.LENGTH_SHORT).show()
            } else if (binding.urlEt.text.toString() == "") {
                Toast.makeText(this, "Set event url.", Toast.LENGTH_SHORT).show()
            } else {
                val event = Event(
                    binding.eventName.text.toString(),
                    binding.description.text.toString(),
                    formatter.parse(binding.btnSetDate.text.toString()),
                    binding.btnSetTime.text.toString(),
                    binding.sizeSpinner.selectedItem as String,
                    binding.urlEt.text.toString()
                )
                eventViewModel.insertEvent(event)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun updateLabelDate(btnDate: Button, myCalendar: Calendar) {
        val myFormat = "MM/dd/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        btnDate.text = sdf.format(myCalendar.time)
    }

    private fun updateLabelTime(btnTime: Button, myCalendar: Calendar) {
        val myFormat = "hh:mm"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        btnTime.text = sdf.format(myCalendar.time)
    }
}