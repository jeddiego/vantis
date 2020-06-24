package com.test.vantis.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.test.vantis.MainApplication
import com.test.vantis.R
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel: ViewModel() {

    private var startDate = ""
    private var endDate = ""

    fun setStartDate(date: String){
        startDate = date
    }

    fun setEndDate(date: String){
        endDate = date
    }

    fun onGenerateReportClick(){
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val currentDate = formatter.format(date)

        if(startDate > endDate){
            showMessage(R.string.error_start_date)
        } else if(endDate > currentDate){
            showMessage(R.string.error_end_date)
        } else {

        }
    }

    private fun showMessage(idMessage: Int) {
        Toast.makeText(
            MainApplication.appContext,
            MainApplication.appResourceProvider.getString(idMessage),
            Toast.LENGTH_SHORT
        ).show()
    }
}