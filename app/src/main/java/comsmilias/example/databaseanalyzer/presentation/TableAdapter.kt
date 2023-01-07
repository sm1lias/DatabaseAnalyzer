package comsmilias.example.databaseanalyzer.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import comsmilias.example.databaseanalyzer.data.remote.dto.TimeStampDto
import comsmilias.example.databaseanalyzer.databinding.TableItemBinding
import comsmilias.example.databaseanalyzer.domain.model.TimeStamp

class TableAdapter() : RecyclerView.Adapter<MyViewHolder>() {
    private val tableData = ArrayList<TimeStamp>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TableItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tableData[position])
    }

    override fun getItemCount() = tableData.size

    fun setList(list: List<TimeStamp>){
        tableData.clear()
        tableData.addAll(list)
    }
}

class MyViewHolder(private val binding: TableItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(timeStamp: TimeStamp) {
        binding.apply {
            txtTest.text = timeStamp.toString()
        }
    }
}