package gachon.third.umc.android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.databinding.ActivityEditProfileBinding
import gachon.third.umc.android.databinding.FragmentProfileBinding

class ActivityEditProfile : AppCompatActivity() {

    lateinit var binding:ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.setText(intent.getStringExtra("name").toString())
        binding.account.setText(intent.getStringExtra("account").toString())
        binding.introduce.setText(intent.getStringExtra("introduce").toString())

        //못나감
        binding.account.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                binding.checkbtn.isEnabled = binding.account.length() != 0
            }
        })

        binding.checkbtn.setOnClickListener {

            val resultIntent = Intent()
            //toString 꼭 적어주기
            resultIntent.putExtra("name",binding.name.text.toString())
            resultIntent.putExtra("account",binding.account.text.toString())
            resultIntent.putExtra("introduce",binding.introduce.text.toString())

            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        binding.closebtn.setOnClickListener {
            finish()
        }
    }
}