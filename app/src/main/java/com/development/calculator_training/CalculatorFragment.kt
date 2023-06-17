package com.development.calculator_training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.development.calculator_training.databinding.FragmentCalculatorBinding
import java.util.Stack

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding
        get() = _binding!!

    private var inputNumber: Int = 0
    private var result: Int? = 0
    private var stackOperator = Stack<String>()
    private var currentOperator: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvent()
    }

    private fun handleEvent() {
        with(binding) {
            buttonClear.setOnClickListener {
                inputNumber = 0
                stackOperator.clear()
                result = 0
                showInputNumber(0)
            }

            buttonOpposite.setOnClickListener {
                result?.let {
                    result = it * -1
                    textViewResult.text = result.toString()
                }
            }

            buttonPercent.setOnClickListener {

            }

            buttonDivide.setOnClickListener {
                if (stackOperator.isEmpty()) {
                    if (result != null) {
                        result = inputNumber
                    }
                } else {
                    if (currentOperator == null) {
                        updateResult(stackOperator.pop())?.let { result ->
                            textViewResult.text = result.toString()
                        } ?: kotlin.run {
                            textViewResult.text = getString(R.string.not_a_number)
                        }
                    } else {
                        stackOperator.pop()
                    }
                }
                currentOperator = "/"
                inputNumber = 0
                stackOperator.push("/")
            }

            buttonSeven.setOnClickListener {
                showInputNumber(7)
            }

            buttonEight.setOnClickListener {
                showInputNumber(8)
            }

            buttonNine.setOnClickListener {
                showInputNumber(9)
            }

            buttonMultiply.setOnClickListener {
                if (stackOperator.isEmpty()) {
                    if (result != null) {
                        result = inputNumber
                    }
                } else {
                    if (currentOperator == null) {
                        textViewResult.text = updateResult(stackOperator.pop()).toString()
                    } else {
                        stackOperator.pop()
                    }
                }
                currentOperator = "*"
                inputNumber = 0
                stackOperator.push("*")
            }

            buttonFour.setOnClickListener {
                showInputNumber(4)
            }

            buttonFive.setOnClickListener {
                showInputNumber(5)
            }

            buttonSix.setOnClickListener {
                showInputNumber(6)
            }

            buttonMinus.setOnClickListener {
                if (stackOperator.isEmpty()) {
                    if (result != null) {
                        result = inputNumber
                    }
                } else {
                    if (currentOperator == null) {
                        textViewResult.text = updateResult(stackOperator.pop()).toString()
                    } else {
                        stackOperator.pop()
                    }
                }
                currentOperator = "-"
                inputNumber = 0
                stackOperator.push("-")
            }

            buttonOne.setOnClickListener {
                showInputNumber(1)
            }

            buttonTwo.setOnClickListener {
                showInputNumber(2)
            }

            buttonThree.setOnClickListener {
                showInputNumber(3)
            }

            buttonPlus.setOnClickListener {
                if (stackOperator.isEmpty()) {
                    if (result != null) {
                        result = inputNumber
                    }
                } else {
                    if (currentOperator == null) {
                        textViewResult.text = updateResult(stackOperator.pop()).toString()
                    } else {
                        stackOperator.pop()
                    }
                }
                currentOperator = "+"
                inputNumber = 0
                stackOperator.push("+")
            }

            buttonZero.setOnClickListener {
                showInputNumber(0)
            }

            buttonDot.setOnClickListener {

            }

            buttonCompare.setOnClickListener {
                if (stackOperator.isNotEmpty()) {
                    updateResult(operator = stackOperator.pop())?.let { result ->
                        inputNumber = result
                        textViewResult.text = inputNumber.toString()
                    } ?: kotlin.run {
                        textViewResult.text = getString(R.string.not_a_number)
                    }
                }
            }
        }
    }

    private fun showInputNumber(number: Int) {
        currentOperator = null
        inputNumber = inputNumber * 10 + number
        binding.textViewResult.text = inputNumber.toString()
    }

    private fun updateResult(operator: String): Int? {
        when (operator) {
            "+" -> {
                result?.let {
                    result = it + inputNumber
                }
            }

            "-" -> {
                result?.let {
                    result = it - inputNumber
                }
            }

            "*" -> {
                result?.let {
                    result = it * inputNumber
                }
            }

            "/" -> {
                try {
                    result?.let {
                        result = it / inputNumber
                    }
                } catch (exception: ArithmeticException) {
                    result = null
                }
            }

            else -> {
                /* no-op */
            }
        }
        return result
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
