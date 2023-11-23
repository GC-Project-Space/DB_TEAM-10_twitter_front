package gachon.third.umc.android

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var getresult: ActivityResultLauncher<Intent>
    private lateinit var mContext: Context
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        //탭 아이콘 설정
        //mContext = requireContext()
        //val tabLayout: TabLayout=binding.profileTab
        //tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(mContext, android.R.color.black))



        //탭 스와이프
        //val tabAdapter = ProfileViewpagerAdapter(this)
        //binding.viewPager.adapter=tabAdapter

        /*TabLayoutMediator(binding.profileTab, binding.viewPager){tab, position ->
            when (position) {
                0 -> {  //탭 아이콘 설정
                    tab.setIcon(R.drawable.ic_postgrid)
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_myinfo_tag)
                }
            }
        }.attach()*/


        //하이라이트 리사이클러뷰
       // val dataList = listOf("신규", "Name 2", "Name 3", "Name 4", "Name 5", "Name 6", "Name 7", "Name 8", "Name 9")


        //val Hadapter = HighlightAdapter(dataList)

        //val HlayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        //binding.highlightRecyclerView.layoutManager = HlayoutManager
        //binding.highlightRecyclerView.adapter = Hadapter

        //사이 사이 공간
        //Hadapter.setItemSpacing(binding.highlightRecyclerView, 25)


        //게시글 리사이클러뷰
        val dataList = listOf(
            StoryItemData("Name1", "1시간 전", R.drawable.my_post),
            StoryItemData("Name2", "2시간 전", R.drawable.my_post),
            StoryItemData("Name3", "3시간 전", R.drawable.my_post),
            StoryItemData("Name4", "4시간 전", R.drawable.my_post),
            StoryItemData("Name5", "5시간 전", R.drawable.my_post),
            StoryItemData("Name6", "6시간 전", R.drawable.my_post),
            StoryItemData("Name7", "7시간 전", R.drawable.my_post),
            StoryItemData("Name8", "8시간 전", R.drawable.my_post),
            StoryItemData("Name9", "9시간 전", R.drawable.my_post))



        val Padapter = PostAdapter( dataList)

        val PlayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.postRecy.layoutManager = PlayoutManager
        binding.postRecy.adapter = Padapter


       //프로필 수정 결과를 처리
        getresult=
            registerForActivityResult(ActivityResultContracts
                .StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    // 결과 처리
                    val data = result.data
                    binding.name.text = data?.getStringExtra("name").toString()
                    binding.account.text = data?.getStringExtra("account").toString()
                    binding.introduce.text = data?.getStringExtra("introduce").toString()

                }
            }

        //입력이 없을 경우 gone
        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                // EditText의 텍스트가 없을 경우 TextView의 속성을 gone으로 변경
                if (s.isNullOrEmpty()) {
                    binding.name.visibility = View.GONE
                } else {
                    binding.name.visibility = View.VISIBLE
                }
            }
        })
        binding.introduce.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }
            override fun afterTextChanged(s: Editable?) {
                // EditText의 텍스트가 없을 경우 TextView의 속성을 gone으로 변경
                if (s.isNullOrEmpty()) {
                    binding.introduce.visibility = View.GONE
                } else {
                    binding.introduce.visibility = View.VISIBLE
                }
            }
        })


        //edit 액티비티 불러오기
        binding.editProfile.setOnClickListener {
            val intent = Intent(activity, ActivityEditProfile::class.java)

            //인텐트에 정보 추가
            intent.putExtra("name", binding.name.text.toString())
            intent.putExtra("account", binding.account.text.toString())
            intent.putExtra("introduce", binding.introduce.text.toString())

            //돌아오는 결과를 받겠다
            getresult.launch(intent)
        }

        //SharedPreferences에 저장된 아이디 정보로 아이디 설정하기
        sharedPreferences = requireActivity().getSharedPreferences("autoLogin", Context.MODE_PRIVATE)
        val userId: String? = sharedPreferences.getString("userId", null)
        if(!userId.isNullOrEmpty()){
            binding.account.text=userId
        }


        return binding.root
    }

}