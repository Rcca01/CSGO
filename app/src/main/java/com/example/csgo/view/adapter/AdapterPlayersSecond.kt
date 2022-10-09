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
import com.example.csgo.model.Players


class AdapterPlayersSecond: RecyclerView.Adapter<AdapterPlayersSecond.MyViewHolder>() {
    var players = mutableListOf<Players>()

    fun setPlayersList(players: List<Players>) {
        this.players = players.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPlayersSecond.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_second_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterPlayersSecond.MyViewHolder, position: Int) {
       val player = players[position]
        holder.nickNameSecond.text = player.firstName
        holder.namePlayerSecond.text = player.name
        Glide.with(holder.itemView.context).load(player.imageURL).into(holder.imagePlayerSecond)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nickNameSecond: TextView = itemView.findViewById(R.id.nickNameSecond)
        var namePlayerSecond: TextView = itemView.findViewById(R.id.namePlayerSecond)
        var imagePlayerSecond: ImageView = itemView.findViewById(R.id.imagePlayerSecond)
    }
}