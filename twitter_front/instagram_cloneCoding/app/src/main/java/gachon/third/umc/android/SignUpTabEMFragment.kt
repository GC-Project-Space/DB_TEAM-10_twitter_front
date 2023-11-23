package gachon.third.umc.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import gachon.third.umc.android.databinding.FragmentProfileBinding
import gachon.third.umc.android.databinding.FragmentSignUpTabEMBinding

class SignUpTabEMFragment : Fragment() {

    private lateinit var binding: FragmentSignUpTabEMBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpTabEMBinding.inflate(inflater,container,false)


        //X아이콘 누르면 텍스트 지우기
        binding.delete.setOnClickListener {
            binding.ID.setText("")
        }

        binding.nextBtn.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() //프레그먼트가 속한 엑티비티 종료
        }

        //아이디 없으면 못넘어감
        binding.ID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                binding.nextBtn.isEnabled = binding.ID.length() > 0
            }
        })
        binding.nextBtn.isEnabled = binding.ID.length() > 0

        // Inflate the layout for this fragment
        return binding.root
    }

}