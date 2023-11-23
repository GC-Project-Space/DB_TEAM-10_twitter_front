package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.ActivitySignUpPhoneEmailBinding

class SIgnUpPhoneEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpPhoneEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpPhoneEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 탭 스와이프
        val viewPager: ViewPager2 = binding.signUpVP2
        val tabLayout: TabLayout = binding.tab

        val adapter = SignUpViewpagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(binding.tab, binding.signUpVP2) { tab, position ->
            // 탭과 뷰페이저를 연결
            when (position) {
                0 -> {
                    tab.text = "전화번호"
                }
                1 -> {
                    tab.text = "이메일"
                }
            }
        }.attach()



    }

    //이 엑티비티가 종료되면, 그동안 쌓인 모든 엑티비티 종료
    override fun finish() {
        super.finish()
        ActivityCompat.finishAffinity(this)
    }

    // 뒤로가기 버튼 처리
    override fun onBackPressed() {
        super.onBackPressed()
    }
}