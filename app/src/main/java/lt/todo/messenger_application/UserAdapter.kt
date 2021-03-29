package lt.todo.messenger_application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(private val context: Context, private val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)  {
        val userNameText: TextView = view.findViewById(R.id.usernameTb)
        val messageText: TextView = view.findViewById(R.id.msgTb)
        val userImage: ImageView = view.findViewById(R.id.userImg)
        val layoutUser: ConstraintLayout = view.findViewById(R.id.layoutUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userNameText.text = user.name
        Glide.with(context).load(user.userImage).placeholder(R.drawable.googleg_standard_color_18).into(holder.userImage)

        holder.layoutUser.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}