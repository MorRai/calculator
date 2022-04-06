package com.rai.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rai.calculator.databinding.FragmentListBinding

class LogsFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logs = requireArguments().getStringArrayList(KEY_LOGS)

        with(binding){
            logsList.layoutManager = LinearLayoutManager(view.context)


            if (logs != null) {
                logsList.adapter = LogsAdapter(logs.toList())
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

   companion object{

       private const val KEY_LOGS = "key_logs"

       fun getInstance(logs: MutableList<String>): LogsFragment{
           return  LogsFragment().apply {
               arguments = bundleOf(
                    KEY_LOGS to logs
               )
           }
       }
   }

}