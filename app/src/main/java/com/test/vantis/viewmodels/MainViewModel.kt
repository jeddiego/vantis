package com.test.vantis.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.vantis.MainApplication
import com.test.vantis.R
import com.test.vantis.di.components.DaggerRepositoryComponent
import com.test.vantis.domains.MainRepository
import com.test.vantis.rest.models.OnMainResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var repository: MainRepository
    private var startDate = ""
    private var endDate = ""
    var data = MutableLiveData<OnMainResponse>()
    val getData: LiveData<OnMainResponse> = data

    init {
        DaggerRepositoryComponent.builder()
            .build().inject(this)
    }

    fun setStartDate(date: String) {
        startDate = date
    }

    fun setEndDate(date: String) {
        endDate = date
    }

    fun onGenerateReportClick() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val currentDate = formatter.format(date)

        if (startDate.isBlank() || endDate.isBlank()) {
            showMessage(R.string.error_dates)
        } else if (startDate > endDate) {
            showMessage(R.string.error_start_date)
        } else if (endDate > currentDate) {
            showMessage(R.string.error_end_date)
        } else {
            repository.getData(startDate, endDate).observeForever {
                data.postValue(it)
            }
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