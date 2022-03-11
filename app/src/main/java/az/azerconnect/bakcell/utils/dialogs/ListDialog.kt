package az.azerconnect.bakcell.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleObserver
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.databinding.DialogRecyclerViewBinding

class ListDialog(private val context: Context?) : LifecycleObserver {
    private lateinit var binding: DialogRecyclerViewBinding
    private var dialog: Dialog? = null

    var onItemClick: ((String, Int) -> Unit?)? = null

    init {
        context?.let { createDialog(it) }
    }

    fun show(list: List<String>?) {
        val adapter = ChooseItemAdapter()
        adapter.submitList(list)
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {text,position->
            onItemClick?.invoke(text,position)
        }

        dialog?.show()
    }

    private fun createDialog(context: Context) {
        binding = DialogRecyclerViewBinding.inflate(LayoutInflater.from(context))

        dialog = Dialog(context, R.style.Theme_Bakcell_Dialog).apply {
            setContentView(binding.root)

            window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes?.windowAnimations = R.style.Theme_Bakcell_Dialog
            }
        }
    }

    fun dismiss(){
        dialog?.dismiss()
    }
}