package gachon.third.umc.android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import gachon.third.umc.android.databinding.ActivityLoginBinding
import gachon.third.umc.android.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    //비밀번호 가시성 판별
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpTxt.setOnClickListener{
            val intent = Intent(this,SignUpIdActivity::class.java)
            setResult(RESULT_OK,intent)
        }


        //로그인 버튼 누르면
        binding.loginBtn.setOnClickListener {

            val id=binding.ID.text.toString()
            val pw=binding.PW.text.toString()
            //아이디 : HANA , 비밀번호 : UMC_4rd_Android 로그인이 성공한 것으로 가정
            if(id=="test" && pw=="1234"){


                //자동 로그인
                if(binding.autoLoginCB.isChecked){
                    //SharedPreferences에 아이디와 비밀번호 저장
                    //autoLogin 이름의 SharedPreferences
                    val sharedPreferences: SharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("userId", id) //HANA
                    editor.putString("passwordNo", pw) //UMC_4rd_Android
                    editor.apply()

                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("key", "login")
                    startActivity(intent)

                }else{

                    val intent = Intent(this,MainActivity::class.java)


                    startActivity(intent)

                }
                finish()

            }else{
                val mg="로그인 실패"
                Toast.makeText(this,mg, Toast.LENGTH_SHORT).show()
            }

        }

        //회원가입 화면으로 넘어가기
        binding.signUpTxt.setOnClickListener {
            val intent = Intent(this, SignUpIdActivity::class.java)
            startActivity(intent)
        }



        //비밀번호 가시성 설정
        val showPasswordButton: ImageButton = findViewById(binding.pwBtn.id)
        val passwordEditText: EditText = findViewById(binding.PW.id)
        toggleShowPassword(passwordEditText)

        showPasswordButton.setOnClickListener {
            toggleShowPassword(passwordEditText)
            isPasswordVisible = !isPasswordVisible
        }



        //비밀번호 없으면 못넘어감
        binding.PW.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                binding.pwBtn.isEnabled = binding.PW.length() != 0
            }
        })
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