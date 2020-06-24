package com.test.vantis

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.test.vantis.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var startDate = ""
    private var endDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =  ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        binding.etStartDate.onFocusChangeListener =
            OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    picker(true)
                }
            }

        binding.etEndDate.onFocusChangeListener =
            OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    picker(false)
                }
            }

        binding.btGenerateReport.setOnClickListener {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val date = Date()
            val currentDate = formatter.format(date)

            if(startDate > endDate){
                showMessage(getString(R.string.error_start_date))
            } else if(endDate > currentDate){
                showMessage(getString(R.string.error_end_date))
            } else {
                showMessage("Todo bien")
            }
        }
    }

    fun picker(startDate: Boolean) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val fMonth = if (monthOfYear < 9) {
                    "0" + (monthOfYear + 1)
                } else {
                    monthOfYear + 1
                }
                val fDay = if (dayOfMonth < 10) {
                    "0$dayOfMonth"
                } else {
                    dayOfMonth
                }

                if (startDate) {
                    this.startDate = "$year-$fMonth-$fDay"
                    viewModel.setStartDate(this.startDate)
                    binding.etStartDate.setText(this.startDate)
                } else {
                    this.endDate = "$year-$fMonth-$fDay"
                    viewModel.setEndDate(this.endDate)
                    binding.etEndDate.setText(this.endDate)
                }
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}