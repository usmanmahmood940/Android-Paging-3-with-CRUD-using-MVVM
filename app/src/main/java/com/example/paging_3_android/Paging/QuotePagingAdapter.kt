package com.example.paging_3_android.Paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging_3_android.R
import com.example.paging_3_android.models.Result
import com.example.paging_3_android.models.SampleViewEvents
import com.example.paging_3_android.viewModels.QuoteViewModel

@ExperimentalPagingApi
class QuotePagingAdapter(private val quoteViewModel: QuoteViewModel) : PagingDataAdapter<Result, QuotePagingAdapter.QuoteViewHolder>(COMPARATOR) {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.tvQuote)
        val btnEdit = itemView.findViewById<Button>(R.id.btnEdit)
        val btnRemove = itemView.findViewById<Button>(R.id.btnRemove)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position) ?: return
        if (item != null){
            holder.quote.text = item.content
        }
        holder.btnEdit.setOnClickListener {
            item.content = "Content"
           quoteViewModel.onViewEvent(SampleViewEvents.Edit(item))
        }
        holder.btnRemove.setOnClickListener {
            quoteViewModel.onViewEvent(SampleViewEvents.Remove(item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote_layout, parent, false)
        return QuoteViewHolder(view)
    }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

}