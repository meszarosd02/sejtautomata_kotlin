package com.example.sejtautomata_kotlin.twodimensional

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sejtautomata_kotlin.databinding.RuleItemBinding

class RulesAdapter(private var rules: ArrayList<Rule>, private var onRuleClicked: (Rule) -> Unit): RecyclerView.Adapter<RulesAdapter.ViewHolder>() {
    class ViewHolder(private var binding: RuleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(r: Rule){
            binding.neighborType.text = "Neighbors"
            binding.condition.text = r.comp.toString()
            binding.startingCell.text = r.start.toString()
            binding.result.text = r.result.toString()
            binding.numbers.text = r.num.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RuleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return rules.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = rules[position]
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onRuleClicked(current)
        }
    }

}