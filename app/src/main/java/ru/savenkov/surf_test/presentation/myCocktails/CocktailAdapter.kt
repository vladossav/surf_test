package ru.savenkov.surf_test.presentation.myCocktails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.savenkov.surf_test.R
import ru.savenkov.surf_test.domain.entities.Cocktail
import ru.savenkov.surf_test.util.RecyclerDiffUtil

class CocktailAdapter(private val onClick: (Long) -> Unit): RecyclerView.Adapter<CocktailViewHolder>() {
    var cocktailList: List<Cocktail> = emptyList()
        set(value) {
            val difResult = DiffUtil.calculateDiff(RecyclerDiffUtil(field, value))
            field = value
            difResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder =
        CocktailViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.onBind(cocktailList[position])
    }

    override fun getItemCount(): Int = cocktailList.size
}

class CocktailViewHolder(parent: ViewGroup, private val onClick: (Long) -> Unit): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.cocktail_card_item, parent, false)
)  {

    fun onBind(item: Cocktail) {
        itemView.findViewById<TextView>(R.id.title).text = item.title

        itemView.setOnClickListener {
            onClick.invoke(item.id)
        }
    }
}