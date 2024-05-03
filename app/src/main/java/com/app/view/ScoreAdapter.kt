package com.app.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.app.R


class scoreAdapter(private val mContext: Context, private val scoreListView: MutableList<ScoreItems>):ArrayAdapter<ScoreItems>(mContext,0,scoreListView){

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val scores = scoreListView[position]
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_layout,null)
        val scoreItem = layout.findViewById<TextView>(R.id.scoreListView)
        val userItem = layout.findViewById<TextView>(R.id.userListView)
        val posItem = layout.findViewById<TextView>(R.id.posListView)
        //inflate(R.layout.item_layout, false)

        scoreItem.text = scores.score
        userItem.text = scores.user
        posItem.text = scores.pos

        return layout
    }
}