package gachon.third.umc.android

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import gachon.third.umc.android.databinding.ActivityStoryViewBinding
import gachon.third.umc.android.databinding.FragmentHomeBinding
import gachon.third.umc.android.databinding.StoryItemBinding

class HomeFragment: Fragment(), StoryAdapter.StoryItemClickListener {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

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
        val Sadapter = StoryAdapter(dataList, this)

        val SlayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val PlayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.postRecyclerView.layoutManager = PlayoutManager
        binding.postRecyclerView.adapter = Padapter

        binding.storyRecyclerView.layoutManager = SlayoutManager
        binding.storyRecyclerView.adapter = Sadapter

        //사이 사이 공간
        Padapter.setItemSpacing(binding.postRecyclerView, 4)
        Sadapter.setItemSpacing(binding.storyRecyclerView, 25)


        return binding.root

    }

    override fun onStoryItemClick(storyItem: StoryItemData) {

        //스토리뷰를 생성하고 사용자 이름, 몇 시간 전 글자, 스토리 이미지를 전달
        val inflater=LayoutInflater.from(requireContext())

        var Sbinding = ActivityStoryViewBinding.inflate(layoutInflater)

        // 화면을 storyView로 전환
        val intent= Intent(activity,ActivityStoryView::class.java)

        intent.putExtra("name", storyItem.name)
        intent.putExtra("time", storyItem.time)
        intent.putExtra("storyImg", storyItem.storyImg)

        startActivity(intent)

    }

}