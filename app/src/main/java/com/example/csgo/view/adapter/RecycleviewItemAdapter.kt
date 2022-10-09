package com.example.csgo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.csgo.R
import com.example.csgo.model.Matches
import com.example.csgo.model.Result
import com.example.csgo.utils.Utils
import org.w3c.dom.Text

const val FIRST_OPPONENT = 0
const val SECOND_OPPONENT = 1

class RecycleviewItemAdapter: RecyclerView.Adapter<RecycleviewItemAdapter.MyViewHolder>() {
    var matches = mutableListOf<Matches>()

    fun setMatchesList(matches: List<Matches>) {
        this.matches = matches.toMutableList()
        notifyDataSetChanged()
    }

    private var clickListener: ClickListener<List<Result>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = matches[position].opponents
        if(item.size == 2){
            Glide.with(holder.itemView.context).load(item[FIRST_OPPONENT].opponent.imageURL).into(holder.imageClub1)
            holder.nameClub1.text = item[FIRST_OPPONENT].opponent.name

            Glide.with(holder.itemView.context).load(item[SECOND_OPPONENT].opponent.imageURL).into(holder.imageClub2)
            holder.nameClub2.text = item[SECOND_OPPONENT].opponent.name

            Glide.with(holder.itemView.context).load(matches[position].league.imageURL).into(holder.leagueImage3)
            holder.nameLeague.text = matches[position].league.name

            holder.timeGame.text = Utils().getDayFromDateString(matches[position].scheduledAt)

            holder.itemLayout.setOnClickListener { v -> clickListener!!.onClick(
                matches[position].results,
                item,
                matches[position].league.name,
                matches[position].scheduledAt
            ) }
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun setOnItemClickListener(clickListener: ClickListener<List<Result>>) {
        this.clickListener = clickListener
    }

    override fun getItemId(position: Int): Long {
        return matches[position].id
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageClub1: ImageView = itemView.findViewById(R.id.clubImage1)
        var imageClub2: ImageView = itemView.findViewById(R.id.clubImage2)
        var leagueImage3: ImageView = itemView.findViewById(R.id.leagueImage3)
        var nameClub1: TextView = itemView.findViewById(R.id.clubName1)
        var nameClub2: TextView = itemView.findViewById(R.id.clubName2)
        var nameLeague: TextView = itemView.findViewById(R.id.nameLeague)
        val itemLayout: CardView = itemView.findViewById(R.id.itemLayout)
        val timeGame: TextView = itemView.findViewById(R.id.timeGame)

    }
}