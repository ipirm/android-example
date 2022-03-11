package az.azerconnect.bakcell.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import az.azerconnect.bakcell.databinding.LoadStateFooterBinding
import az.azerconnect.bakcell.utils.bindingAdapters.setOnSingleClickListener
import timber.log.Timber

class LoadStateFooterAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateFooterAdapter.ItemHolder>() {

    inner class ItemHolder(val binding: LoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            Timber.e(loadState.toString())

            binding.retryBtn.isVisible = loadState is LoadState.Error
            binding.progressBar.isVisible = loadState is LoadState.Loading

            binding.retryBtn.setOnSingleClickListener { retry() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemHolder {
        return ItemHolder(
            LoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}