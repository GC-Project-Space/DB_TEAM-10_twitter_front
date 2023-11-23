package gachon.third.umc.android

import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.databinding.ActivityStoryViewBinding

class ActivityStoryView : AppCompatActivity() {

    private lateinit var binding: ActivityStoryViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityStoryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTxt.text = intent.getStringExtra("name")
        binding.timeTxt.text = intent.getStringExtra("time")
        val storyImg = intent.getIntExtra("storyImg", 0)

        binding.storyImg.setImageResource(storyImg)


        //seekbar 터치 불가
        binding.seekBar.max=5000
        binding.seekBar.setOnTouchListener { _, _ -> true }
        binding.seekBar.isClickable=false
        binding.seekBar.isFocusable=false

        //5초 타이머
        val timer = object : CountDownTimer(5000,10){
            override fun onTick(untilFinished: Long) {
                // 남은 시간을 SeekBar에 반영
                binding.seekBar.progress = (5000 - untilFinished).toInt()

            }

            override fun onFinish() {
                finish()
            }
        }

        //타이머 시작
        timer.start()


    }
}