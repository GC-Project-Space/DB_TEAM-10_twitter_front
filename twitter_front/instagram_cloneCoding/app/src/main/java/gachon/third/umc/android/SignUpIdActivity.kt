package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivitySignUpIdBinding

class SignUpIdActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextpage.setOnClickListener {
            val intent= Intent(this, SignUpPwActivity::class.java)
            startActivity(intent)
        }

        //아이디 없으면 못넘어감
        binding.ID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                binding.nextpage.isEnabled = binding.ID.length() > 0
            }
        })
        binding.nextpage.isEnabled = binding.ID.length() > 0


    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 이전 엑티비티로 이동
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}