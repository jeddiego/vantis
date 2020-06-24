package com.test.vantis

import androidx.lifecycle.ViewModel

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

    }
}