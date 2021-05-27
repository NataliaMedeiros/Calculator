package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstNumber: String? = null
    private var secondNumber: String? = null
    private var action: String? = null
    private var isDisplayingResult = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.one.setOnClickListener { writeNumber(binding.one) }
        binding.two.setOnClickListener { writeNumber(binding.two) }
        binding.three.setOnClickListener { writeNumber(binding.three) }
        binding.four.setOnClickListener { writeNumber(binding.four) }
        binding.five.setOnClickListener { writeNumber(binding.five) }
        binding.six.setOnClickListener { writeNumber(binding.six) }
        binding.seven.setOnClickListener { writeNumber(binding.seven) }
        binding.eight.setOnClickListener { writeNumber(binding.eight) }
        binding.nine.setOnClickListener { writeNumber(binding.nine) }
        binding.zero.setOnClickListener { writeNumber(binding.zero) }
        binding.dot.setOnClickListener { writeNumber(binding.dot) }
        binding.erase.setOnClickListener { erase() }
        binding.delete.setOnClickListener { delete() }
        binding.sum.setOnClickListener { sum() }
        binding.equal.setOnClickListener { equal() }
        binding.minus.setOnClickListener { minus() }
        binding.multiplication.setOnClickListener { multiplication() }
        binding.division.setOnClickListener { division() }
        binding.percent.setOnClickListener { percent() }
        binding.undefined.setOnClickListener { startActivity(Intent(this, Calculate2::class.java)) }
    }

    private fun writeNumber(button: Button) {
        if (isDisplayingResult) {
            erase()
        }
        binding.screen.text = ("${binding.screen.text}${button.text}")

    }

    private fun erase() {
        binding.screen.text = ""
        firstNumber = null
        secondNumber = null
        action = null
        isDisplayingResult = false
    }

    private fun delete() {
        binding.screen.text = binding.screen.text.dropLast(1)
    }

    private fun saveFirstNumberAndErase() {
        if (binding.screen.text.toString().isNotBlank()) {
            firstNumber = binding.screen.text.toString()
        }
        binding.screen.text = ""
    }

    private fun saveSecondNumberAndErase() {
        if (binding.screen.text.toString().isNotBlank()) {
            secondNumber = binding.screen.text.toString()
        }
        binding.screen.text = ""
    }

    private fun saveScreen(){
        if (firstNumber == null) {
            saveFirstNumberAndErase()
        } else {
            saveSecondNumberAndErase()
        }
    }
    private fun sum() {
        saveScreen()
        action = "sum"
    }

    fun minus() {
        saveScreen()
        action = "minus"

    }

    private fun multiplication() {
       saveScreen()
        action = "multiplication"
    }

    private fun division() {
        saveScreen()
        action = "division"
    }

    private fun percent() {
        val screenNumber = binding.screen.text.toString().toDouble()
        if (action == null) {
            action = "percent"
            firstNumber = "${screenNumber / 100}"
        } else {
            if (action == "sum" || action == "minus") {
                secondNumber = "${firstNumber!!.toDouble() * screenNumber / 100}"
                binding.screen.text = ""
            } else {
                secondNumber = "${screenNumber / 100}"
                binding.screen.text = ""
            }
        }

    }

    private fun equal() {
        isDisplayingResult = true
        if (binding.screen.text.toString().isNotBlank() && secondNumber == null) {
            secondNumber = binding.screen.text.toString()
        }
        if (firstNumber != null && secondNumber != null) {
            binding.screen.text = when (action) {
                "sum" -> getString(
                    R.string.result,
                    firstNumber,
                    "+",
                    secondNumber,
                    firstNumber!!.toDouble() + secondNumber!!.toDouble()
                )
                "minus" -> getString(
                    R.string.result,
                    firstNumber,
                    "-",
                    secondNumber,
                    firstNumber!!.toDouble() - secondNumber!!.toDouble()
                )
                "multiplication" -> getString(
                    R.string.result,
                    firstNumber,
                    "X",
                    secondNumber,
                    firstNumber!!.toDouble() * secondNumber!!.toDouble()
                )
                "division" ->
                    if (secondNumber!!.toDouble() != 0.0) {
                        getString(
                            R.string.result,
                            firstNumber,
                            "/",
                            secondNumber,
                            firstNumber!!.toDouble() / secondNumber!!.toDouble()
                        )
                    } else {
                        "Can't divide by zero"
                    }
                "percent" -> firstNumber
                else -> secondNumber
            }
        }
    }
}







