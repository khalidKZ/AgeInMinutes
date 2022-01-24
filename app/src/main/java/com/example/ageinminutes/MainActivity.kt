package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)


        btnDatePicker.setOnClickListener {
            clickDatePicker()


        }

    }
//طريقة اظهار التاريخ اذا تم النقر على الزر
    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
       val day = myCalendar.get(Calendar.DAY_OF_MONTH)
//     المتغير دي بي دي ياخذ دالة داتا بيكر دايلوق عشان اقدر الغي الايام المستقبلية
        val dpd = DatePickerDialog(
//            الكود الي تحت مساول عن ربط الصفات بي المتغيرات الي فوق و اظهار التاريخ
                this,
                { View, selectedYear, selectedMonth, selectedDayOfMonth ->
//                 ____________________________________________________
                    Toast.makeText(this, "YEAR WAS $selectedYear, month was ${selectedMonth+1},dat was $selectedDayOfMonth", Toast.LENGTH_LONG).show()
//                 ____________________________________________________
                    val selctedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    tvSelectedDate?.setText(selctedDate)
//                 _______________________________________________________________________

                    val sdf = SimpleDateFormat("dd/MM/yyy",Locale.ENGLISH)
                    val theDate = sdf.parse(selctedDate)
//                 _______________________________________________________________________
                    theDate?.let {

                        val selctedDateInManutes = theDate.time / 60000
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                        currentDate?.let {
                            val currentDateInMinutes = currentDate.time / 60000
                            val differenceInMinutes = currentDateInMinutes - selctedDateInManutes
                            tvAgeInMinutes?.text = differenceInMinutes.toString()
                        }
                    }
//                 _______________________________________________________________________

                },
                year,
                month,
                day
        )
//وهنا الغاء ايام المستقبل
    dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
  dpd.show()

    }
}