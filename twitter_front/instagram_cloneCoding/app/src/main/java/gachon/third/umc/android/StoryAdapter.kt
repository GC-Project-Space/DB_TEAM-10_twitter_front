package gachon.third.umc.android

import android.content.ClipData
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.ActivityMainBinding
import gachon.third.umc.android.databinding.ItemStoryPlusBinding
import gachon.third.umc.android.databinding.StoryItemBinding

class StoryAdapter(private val dataList: List<StoryItemData>, private val itemClickListener: StoryItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //// 스토리 생성 아이템 뷰와 스토리 아이템 뷰를 구분하기 위한 ViewType 상수

    val VIEW_TYPE_STORY_PLUS = 0
    val  VIEW_TYPE_STORY = 1


    // 뷰홀더를 정의 (2개)
    //스토리 뷰홀터
    inner class storyViewHolder(private val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.account.text = item

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val storyItem = dataList[position-1]
                    // 클릭 이벤트 처리
                    itemClickListener.onStoryItemClick(storyItem)
                }
            }

        }
    }
    //스토리 추가 뷰홀더
    inner class plusViewHolder(private val binding: ItemStoryPlusBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    // 뷰홀더를 생성하여 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_STORY_PLUS -> {
                val binding = ItemStoryPlusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                plusViewHolder(binding)
            }
            VIEW_TYPE_STORY -> {
                val binding = StoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                storyViewHolder(binding)

            }
            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    // 뷰홀더의 데이터를 설정
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEW_TYPE_STORY -> {
                val viewHolder = holder as storyViewHolder
                val item = dataList[position - 1]
                viewHolder.bind(item.name)
            }
            VIEW_TYPE_STORY_PLUS -> {
                // Do nothing
            }
            else -> throw IllegalArgumentException("Invalid viewType: ${holder.itemViewType}")
        }
    }

    //스토리 생성, 스토리 아이템 띄우기
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            // 첫 번째 아이템은 스토리 생성 아이템
            VIEW_TYPE_STORY_PLUS
        } else {
            // 나머지 아이템은 스토리 아이템
            VIEW_TYPE_STORY
        }
    }

    //간격 설정을 위한 클래스
    class CustomItemDecoration(private val spaceWidth: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = spaceWidth // 아이템 사이의 간격을 설정, (오른쪽 넓이)
        }
    }
    // 아이템 간격 설정을 위한 메소드
    fun setItemSpacing(recyclerView: RecyclerView, spacing: Int) {
        recyclerView.addItemDecoration(CustomItemDecoration(spacing))
    }

    // 리스트의 항목 수를 반환
    override fun getItemCount(): Int {
        return dataList.size
    }


    //아이템 클릭 이벤트를 처리하는 인터페이스
    interface StoryItemClickListener {
        fun onStoryItemClick(storyItem: StoryItemData) //데이터셋
    }
}