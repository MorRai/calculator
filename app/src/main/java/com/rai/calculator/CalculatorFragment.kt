package com.rai.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rai.calculator.databinding.CalculatorFragmentBinding

class CalculatorFragment : Fragment() {
    private var _binding: CalculatorFragmentBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalculatorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            button0.setOnClickListener { textFormula.text = textFormula.text.toString() + "0"}
            button1.setOnClickListener { textFormula.text = textFormula.text.toString() + "1"}
            button2.setOnClickListener { textFormula.text = textFormula.text.toString() + "2"}
            button3.setOnClickListener { textFormula.text = textFormula.text.toString() + "3" }
            button4.setOnClickListener { textFormula.text = textFormula.text.toString() + "4" }
            button5.setOnClickListener { textFormula.text = textFormula.text.toString() + "5"}
            button6.setOnClickListener { textFormula.text = textFormula.text.toString() + "6" }
            button7.setOnClickListener { textFormula.text = textFormula.text.toString() + "7" }
            button8.setOnClickListener { textFormula.text = textFormula.text.toString() + "8"  }
            button9.setOnClickListener { textFormula.text = textFormula.text.toString() + "9"  }
            buttonDev.setOnClickListener { textFormula.text = textFormula.text.toString() + "/" }
            buttonMinus.setOnClickListener { textFormula.text = textFormula.text.toString() + "-"  }
            buttonPlus.setOnClickListener { textFormula.text = textFormula.text.toString() + "+"  }
            buttonMult.setOnClickListener { textFormula.text = textFormula.text.toString() + "*" }
            buttonLeftBracket.setOnClickListener { textFormula.text = textFormula.text.toString() + "("  }
            buttonRightBracket.setOnClickListener { textFormula.text = textFormula.text.toString() + ")"  }
            buttonClear.setOnClickListener { textFormula.text = ""  }
            buttonDelChar.setOnClickListener { textFormula.text = textFormula.text.toString().dropLast(1) }

            buttonLogs.setOnClickListener{ pushLogsFragment(logs) }

            buttonCalculate.setOnClickListener {
                try {
                    var stringFormula = "Результат выражения ${textFormula.text}: "
                    textFormula.text = calculate(textFormula.text.toString())
                    stringFormula += textFormula.text
                    logs.add(stringFormula)
                }
                catch (e:Exception){
                    Toast.makeText(context, e.message?:"", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        val logs = mutableListOf<String>()
    }
}