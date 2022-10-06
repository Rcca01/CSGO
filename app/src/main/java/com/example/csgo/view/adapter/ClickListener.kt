package com.example.csgo.view.adapter

import android.view.View

interface ClickListener<T> {
    fun onClick(view: View?, data: T, position: Int)
}