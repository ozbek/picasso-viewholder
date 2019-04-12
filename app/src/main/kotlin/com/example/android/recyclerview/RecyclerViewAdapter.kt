/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_row_item.view.*

/**
 * Provide views to RecyclerView with data from dataSet.
 *
 * Initialize the dataset of the Adapter.
 *
 * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
 */
class RecyclerViewAdapter(private val dataSet: Array<String>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(url: String?) {
            // Define click listener for the ViewHolder's View.
            itemView.imageView.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked.")
            }
            Picasso.get()
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .fit()
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(itemView.imageView, object : Callback {
                        override fun onSuccess() {
                            //no-op
                        }

                        override fun onError(e: Exception?) {
                            // Try again online if cache failed
                            Picasso.get()
                                    .load(url)
                                    .placeholder(R.mipmap.ic_launcher_round)
                                    .fit()
                                    .into(itemView.imageView)
                        }
                    })
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.image_row_item, viewGroup, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }
}
