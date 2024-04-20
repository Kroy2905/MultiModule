package com.kroy.multimodule.data.paging

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Color
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kroy.multimodule.R
import com.kroy.multimodule.data.models.postResponseItem

class PostPagingAdapter  : PagingDataAdapter<postResponseItem, PostPagingAdapter.PostViewHolder>(
    COMPARATOR) {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val id = itemView.findViewById<TextView>(R.id.Id)
        val userID = itemView.findViewById<TextView>(R.id.userId)
        val title = itemView.findViewById<TextView>(R.id.searchitem_title)
        val body = itemView.findViewById<TextView>(R.id.body)
        val bodyLayout = itemView.findViewById<LinearLayout>(R.id.bodyLayout)
        val root = itemView.findViewById<CardView>(R.id.cardView)

        init {
            // Set the click listener for the item
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            // Toggle the visibility of the body text on each click
            Log.d("visibilty check->","${bodyLayout.visibility}")
            bodyLayout.visibility = if (bodyLayout.visibility == View.VISIBLE)
                View.GONE else View.VISIBLE
            val colorCode = 0xFFFFF8E3.toInt()
            root.backgroundTintList = if (bodyLayout.visibility == View.VISIBLE)
                ColorStateList.valueOf(colorCode) else null

        }
    }

    override fun onBindViewHolder(holder: PostPagingAdapter.PostViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.title.text = item.title
            holder.body.text = item.body
            holder.id.text = item.id.toString()
            holder.userID.text = item.userId.toString()

            // Reset the visibility of bodyLayout and background tint of root
            holder.bodyLayout.visibility = if (item.isBodyVisible) View.VISIBLE else View.GONE
            val colorCode = 0xFFFFF8E3.toInt() // Define your color code here
            holder.root.backgroundTintList = if (item.isBodyVisible) ColorStateList.valueOf(colorCode) else null
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PostPagingAdapter.PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PostViewHolder(view)
    }

    companion object{
        private  val COMPARATOR = object :DiffUtil.ItemCallback<postResponseItem>(){
            override fun areItemsTheSame(
                oldItem: postResponseItem,
                newItem: postResponseItem,
            ): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: postResponseItem,
                newItem: postResponseItem,
            ): Boolean {
             return  oldItem == newItem
            }

        }
    }
}