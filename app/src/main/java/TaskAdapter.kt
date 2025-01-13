import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mytodoapp.R

class TaskAdapter(var myContext: Context, var resource:Int, var taskList: MutableList<Task>):
    ArrayAdapter<Task>(myContext,resource,taskList){

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = LayoutInflater.from(myContext).inflate(resource,parent,false)
        val task = taskList[position]
        val title = itemView.findViewById<TextView>(R.id.itemTitle)
        val description = itemView.findViewById<TextView>( R.id.itemDescription)
        val descSize = if(task.description.length >= 99)
        {
            100
        }else{
            task.description.length
        }
        var textDescription = task.description.substring(0,descSize)
        if(textDescription.length >= 99)
        {
            task.description += "..."
        }
        title.text ="NameTask : ${task.title}"
        description.text = "Task: ${task.description}"

        return itemView

    }



}