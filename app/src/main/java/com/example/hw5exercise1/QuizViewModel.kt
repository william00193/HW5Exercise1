package com.example.hw5exercise1

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


//Giving a name to my TAG and Current index Key for reference
private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"



//initiating and extending the QuizViewModel, and adding SavedStateHandle
class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {




//    init {
//        Log.d(TAG, "Quiz View Model Instance Created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//
//        Log.d(TAG, "Quiz View Model is about to be Created")
//
//    }

    //Data from previous activity that is now saved as the current state and view
    private val questionBank = listOf(
        question(R.string.question_australia, true),
        question(R.string.question_oceans, true),
        question(R.string.question_mideast, false),
        question(R.string.question_africa, false),
        question(R.string.question_americas, true),
        question(R.string.question_asia, true),
    )


    //Defining currentIndex as an Integer
//Getting the saved state, and getting the current index key previously defined
    var currentIndex: Int
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



//Saving the savedStateHandle for IS_CHEATER_KEY
    var isCheater: Boolean
    get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
    set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)




    //Newly defined function that allows the user to move forward a questions, and progress the question bank by 1
//Also incrementing the current questionCount by 1
    fun moveNext() {
        currentIndex = (currentIndex + 1 % questionBank.size)
    }
}


//Newly defined function that allows the user to move back a questions, and progress the question bank by 1
//Also incrementing the current questionCount by 1
//    fun movePrevious() {
//        currentIndex = (currentIndex - 1 % questionBank.size)
//    }
//
//}