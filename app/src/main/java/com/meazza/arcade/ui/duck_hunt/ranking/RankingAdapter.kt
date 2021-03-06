package com.meazza.arcade.ui.duck_hunt.ranking

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meazza.arcade.R
import com.meazza.arcade.common.inflate
import com.meazza.arcade.model.Score
import kotlinx.android.synthetic.main.item_ranking.view.*

object RankingAdapter : RecyclerView.Adapter<RankingAdapter.Holder>() {

    private var dataList = mutableListOf<Score>()

    fun setListData(data: MutableList<Score>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(parent.inflate(R.layout.item_ranking))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(dataList[position])
    }

    override fun getItemCount() = if (dataList.size > 0) dataList.size else 0

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(score: Score) = with(itemView) {
            tv_user_name.text = score.userName
            tv_points.text = score.points.toString()
        }
    }
}