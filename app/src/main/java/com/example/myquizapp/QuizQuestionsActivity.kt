package com.example.myquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var progress_bar: ProgressBar? = null
    private var tv_progress: TextView? = null
    private var tv_question: TextView? = null
    private var iv_image: ImageView? = null
    private var tv_option_one: TextView? = null
    private var tv_option_two: TextView? = null
    private var tv_option_three: TextView? = null
    private var tv_option_four: TextView? = null
    private var btn_submit: Button? = null

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progress_bar = findViewById(R.id.progress_bar)
        tv_progress = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)
        iv_image = findViewById(R.id.iv_image)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)

        tv_option_one?.setOnClickListener(this)
        tv_option_two?.setOnClickListener(this)
        tv_option_three?.setOnClickListener(this)
        tv_option_four?.setOnClickListener(this)
        btn_submit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestion()

        setQuestion()
    }

    private fun setQuestion() {
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        val progressText = mCurrentPosition.toString() + "/" + progress_bar?.max

        progress_bar?.progress = mCurrentPosition
        tv_progress?.text = progressText
        tv_question?.text = question.question
        iv_image?.setImageResource(question.image)
        tv_option_one?.text = question.optionOne
        tv_option_two?.text = question.optionTwo
        tv_option_three?.text = question.optionThree
        tv_option_four?.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit?.text = "FINISH"
        } else {
            btn_submit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        tv_option_one?.let {
            options.add(0, it)
        }
        tv_option_two?.let {
            options.add(1, it)
        }
        tv_option_three?.let {
            options.add(2, it)
        }
        tv_option_four?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                tv_option_one?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tv_option_two -> {
                tv_option_two?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tv_option_three -> {
                tv_option_three?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tv_option_four -> {
                tv_option_four?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_submit ->{

            }
        }
    }
}
