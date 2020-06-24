package com.test.vantis.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.vantis.viewmodels.MainViewModel
import com.test.vantis.R
import com.test.vantis.databinding.ActivityMainBinding
import com.test.vantis.rest.models.OnMainResponse
import java.util.*
import androidx.lifecycle.Observer
import com.test.vantis.di.components.DaggerViewModelComponent
import com.test.vantis.di.modules.ContextModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MainViewModel
    private var startDate = ""
    private var endDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        DaggerViewModelComponent.builder().contextModule(ContextModule(this)).build().inject(this)
        binding.viewModel = viewModel
        bindView()
        binding.etStartDate.onFocusChangeListener =
            OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    picker(true)
                }
            }

        binding.etEndDate.onFocusChangeListener =
            OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    picker(false)
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
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
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

    private fun bindView(){
        viewModel.getData.observe(this, Observer(this::setData))
    }

    private fun setData(response: OnMainResponse) {
        if(response.codeMessage == "0") {
            binding.tvResult.visibility = View.VISIBLE
            binding.tvResult.text =
                "responseObject: ${response.responseObject}\ncodeMessage: ${response.codeMessage}\nresponseMessage: ${response.responseMessage}"
        } else {
            Toast.makeText(this, response.responseMessage, Toast.LENGTH_LONG).show()
        }
    }

}