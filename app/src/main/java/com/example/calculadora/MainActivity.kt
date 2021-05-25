package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var action: String? = null
    private var actionTwo: String? = null

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
        binding.undefined.setOnClickListener { startActivity(Intent(this, Calculate2::class.java))}
    }

    private fun writeNumber(button: Button) {
        if (binding.screen.text == "0"){
            binding.screen.text =""
        }
        if (actionTwo == "equal") {
            erase()
            binding.screen.text = ("${binding.screen.text}${button.text}")
            actionTwo = ""
        } else {
            binding.screen.text = ("${binding.screen.text}${button.text}")
        }
    }

    private fun erase() {
        binding.screen.text = ""
        firstNumber = 0.0
        secondNumber = 0.0
        action = null
        actionTwo = null
    }

    private fun delete() {
        binding.screen.text = binding.screen.text.dropLast(1)
    }

    private fun savefirstNumberAndErase() {
        if (binding.screen.text.toString().isNotBlank()) {
            firstNumber = binding.screen.text.toString().toDouble()
        }
        binding.screen.text = ""
    }

    private fun saveSecondNumberAndErase() {
        if (binding.screen.text.toString().isNotBlank()) {
            secondNumber = binding.screen.text.toString().toDouble()
        }
        binding.screen.text = ""
    }

    private fun sum() {
        if (firstNumber == 0.0) {
            savefirstNumberAndErase()
        } else {
            saveSecondNumberAndErase()
        }
        action = "sum"
    }

    fun minus() {
        if (firstNumber == 0.0) {
            savefirstNumberAndErase()
        } else {
            saveSecondNumberAndErase()
        }
        action = "minus"

    }

    private fun multiplication() {
        if (firstNumber == 0.0) {
            savefirstNumberAndErase()
        } else {
            saveSecondNumberAndErase()
        }
        action = "multiplication"
    }

    private fun division() {
        if (firstNumber == 0.0) {
            savefirstNumberAndErase()
        } else {
            saveSecondNumberAndErase()
        }
        action = "division"
    }

    private fun percent() {
        if (firstNumber != 0.0) {
            if (action == "sum" || action == "minus") {
                secondNumber = firstNumber * (binding.screen.text.toString().toDouble() / 100)
                binding.screen.text = ""
            } else {
                secondNumber = binding.screen.text.toString().toDouble() / 100
                binding.screen.text = ""
            }
            //  saveSecondNumberAndErase()
        } else {
            firstNumber = binding.screen.text.toString().toDouble() / 100
            binding.screen.text = ""
        }
        if (action != null) {
            actionTwo = "percent"
        }

    }

    private fun equal() {
        actionTwo = "equal"
        if (binding.screen.text.toString().isNotBlank()) {
            if (secondNumber == 0.0) {
                secondNumber = binding.screen.text.toString().toDouble()
            }
        }
        when (action) {
            "sum" -> binding.screen.text = getString(R.string.result, firstNumber, "+", secondNumber, firstNumber + secondNumber)
            "minus" -> binding.screen.text = getString(R.string.result, firstNumber, "-", secondNumber, firstNumber - secondNumber)
            "multiplication" -> binding.screen.text = getString(R.string.result, firstNumber, "X", secondNumber, firstNumber * secondNumber)
            "division" ->
                if (secondNumber != 0.0) {
                    binding.screen.text = getString(R.string.result, firstNumber, "/", secondNumber, firstNumber / secondNumber)
                } else {
                    binding.screen.text = ("Can't divide by zero")
                }
            else -> binding.screen.text = "$secondNumber"
        }
    }
}







