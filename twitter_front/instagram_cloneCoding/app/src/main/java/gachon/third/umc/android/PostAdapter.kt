package gachon.third.umc.android

import android.graphics.Rect
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import gachon.third.umc.android.databinding.PostItemBinding

class PostAdapter(private val dataList: List<StoryItemData>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    // 뷰홀더를 정의
    inner class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.account.text = item
        }
    }

    // 뷰홀더를 생성하여 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 뷰홀더의 데이터를 설정
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]
        holder.bind(item.name)

        // 이름 텍스트를 굵게 표시
        val commentText = holder.itemView.findViewById<TextView>(R.id.cmt)
        //holder.itemView는 RecyclerView.ViewHolder가 갖고 있는 View의 루트
        val spannable = SpannableStringBuilder(commentText.text)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        commentText.text = spannable


    }

    //간격 설정을 위한 클래스
    class CustomItemDecoration(private val spaceHeight: Int) : ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.top = spaceHeight // 아이템 사이의 간격을 설정 (위 넓이)
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


}
