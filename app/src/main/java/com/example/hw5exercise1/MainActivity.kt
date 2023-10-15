package com.example.hw5exercise1

import android.app.Activity

import android.os.Bundle

import android.util.Log

import android.widget.Toast

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hw5exercise1.databinding.ActivityMainBinding






//Naming the Tag as 'MainActivity'
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Giving a name to my ViewModel that will be referenced from another page later
    private val quizViewModel: QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    )
            { result ->

                if (result.resultCode == Activity.RESULT_OK) {
                    quizViewModel.isCheater =
                        result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
                }
            }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//Binding and layout Inflator stayed the same as the last few activities
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//Logs that name and define output in the LogCat
        Log.d(TAG, "onCreate (Bundle?) called")
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")



        binding.trueButton.setOnClickListener {

            checkAnswer(true)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

        }




        binding.falseButton.setOnClickListener {

            checkAnswer(false)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)

        }




        binding.questionTextView.setOnClickListener {

            quizViewModel.moveNext()

        }



        binding.nextButton.setOnClickListener {


            binding.falseButton.isEnabled = true
            binding.trueButton.isEnabled = true

//New method of moving forward a question
//Defined in QuizViewModel.kt file
            quizViewModel.moveNext()

            updateQuestion()

        }


//New button created for homework #5
        binding.cheatButton.setOnClickListener {

//val intent = Intent(this,CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)


//startActivity(intent)
            cheatLauncher.launch(intent)

        }

        updateQuestion()

    }





//Update question function
    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText

        binding.questionTextView.setText(questionTextResId)

    }




//Function to check if user answer is correct
    private fun checkAnswer(userAnswer: Boolean) {


//New way of checking if user answer is correct using QuizViewModel.kt
        val correctAnswer = quizViewModel.currentQuestionAnswer



    val messageResId =
            when {
            quizViewModel.isCheater -> R.string.judgement_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else ->  R.string.incorrect_toast
        }


Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
    .show()


    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }



    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }



    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}
