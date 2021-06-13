package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.R
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.databinding.ActivityNewEventBinding
import java.text.SimpleDateFormat
import java.util.*

class NewEventActivity : AppCompatActivity() {
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
    }

    private fun initListeners() {
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
            val hour = myCalendar.get(Calendar.HOUR_OF_DAY)
            val minute = myCalendar.get(Calendar.MINUTE)
            TimePickerDialog(
                this@NewEventActivity, time, myCalendar[Calendar.HOUR_OF_DAY], myCalendar[Calendar.MINUTE], true
            ).show()
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