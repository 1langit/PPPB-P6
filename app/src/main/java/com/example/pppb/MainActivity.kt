package com.example.pppb

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.pppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val provinsi = arrayOf(
            "Jawa Timur",
            "Jawa Tengah",
            "Jawa Barat",
            "DKI Jakarta",
            "DIY"
        )
        with(binding) {
            val adapterProvinsi = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, provinsi)
            spinnerProvinsi.adapter = adapterProvinsi

            val countries = resources.getStringArray(R.array.countries)
            val adapterCountries = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, countries)
            spinnerCountries.adapter = adapterCountries

            spinnerCountries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected (parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(
                        this@MainActivity,
                        countries[position],
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            datePicker.init (datePicker.year, datePicker.month, datePicker.dayOfMonth) {
                _, year, month, dayOfMonth -> val selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(
                    this@MainActivity,
                    selectedDate,
                    Toast.LENGTH_SHORT
                ).show()
            }

            timePickerView.setOnTimeChangedListener {
                _, hourOfDay, minute -> val selectedTime = "$hourOfDay:$minute"
                Toast.makeText(
                    this@MainActivity,
                    selectedTime,
                    Toast.LENGTH_SHORT
                ).show()
            }

            btnShowCalendar.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "datePicker")
            }

            btnShowTime.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timePicker")
            }
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val selectedDate = "$p3/${p2 + 1}/$p1"
        Toast.makeText(
            this@MainActivity,
            selectedDate,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onTimeSet(p0: android.widget.TimePicker?, p1: Int, p2: Int) {
        val selectedTime = "$p2:$p1"
        Toast.makeText(
            this@MainActivity,
            selectedTime,
            Toast.LENGTH_SHORT
        ).show()
    }
}