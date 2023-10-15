package com.example.hw5exercise1

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


//Giving a name to my TAG and Current index Key for reference
//private const val TAG = "QuizViewModel"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
private const val IS_CHEATER_KEY = "IS_CHEATER_KEY"





//initiating and extending the QuizViewModel, and adding SavedStateHandle
class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {




    //Data from previous activity that is now saved as the current state and view
    private val questionBank = listOf(
        question(R.string.question_australia, true),
        question(R.string.question_oceans, true),
        question(R.string.question_mideast, false),
        question(R.string.question_africa, false),
        question(R.string.question_americas, true),
        question(R.string.question_asia, true),
    )



//Saving the savedStateHandle for IS_CHEATER_KEY
    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)


//Defining currentIndex as an Integer
//Getting the saved state, and getting the current index key previously defined
    private var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


//Defining currentQuestionAnswer index as True/False
//Getting this variable from current index of question banks answer section
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer


//Defining currentQuestionText as an integer
//Getting this variable from the current index of question banks Text section
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId





//Newly defined function that allows the user to move forward a questions, and progress the question bank by 1
//Incrementing the current questionCount by 1
    fun moveNext() {
        currentIndex = (currentIndex + 1) % questionBank.size

    }
}



//Newly defined function that allows the user to move back a questions, and progress the question bank by 1
//Incrementing the current questionCount by -1
//    fun movePrevious() {
//        currentIndex = (currentIndex - 1 % questionBank.size)
//    }
//
//}