package com.rai.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view,CalculatorFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}

fun Fragment.pushLogsFragment(logs: MutableList<String>){
    (requireActivity() as MainActivity)
        .supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container_view,LogsFragment.getInstance(logs))
        .addToBackStack(null)
        .commit()
}