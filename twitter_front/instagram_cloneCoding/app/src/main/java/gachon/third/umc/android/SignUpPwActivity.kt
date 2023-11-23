package gachon.third.umc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import gachon.third.umc.android.databinding.ActivitySignUpPwBinding

class SignUpPwActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpPwBinding

    //비밀번호 가시성 판별
    private var isPasswordVisible: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener {
            val intent= Intent(this, SIgnUpPhoneEmailActivity::class.java)
            startActivity(intent)
        }

        //비밀번호 6미만이면 못넘어감
        binding.PW.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                //6자리 이상 입력했을 때 활성화
                binding.nextBtn.isEnabled = binding.PW.length() >= 6
            }
        })

        //비밀번호 가시성 설정
        val showPasswordButton: ImageButton = findViewById(binding.pwBtn.id)
        val passwordEditText: EditText = findViewById(binding.PW.id)
        toggleShowPassword(passwordEditText)

        showPasswordButton.setOnClickListener {
            toggleShowPassword(passwordEditText)
            isPasswordVisible = !isPasswordVisible
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 이전 엑티비티로 이동
        val intent = Intent(this, SignUpIdActivity::class.java)
        startActivity(intent)
    }

    //비밀번호 가시성 설정
    fun toggleShowPassword(editText: EditText) {

        if (isPasswordVisible) {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.pwBtn.setImageResource(R.drawable.ic_pwd_off)
        } else {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.pwBtn.setImageResource(R.drawable.ic_pwd_on) //버튼 이미지 on으로 설정
        }
    }
}