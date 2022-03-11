package az.azerconnect.bakcell.utils.bindingAdapters

import androidx.databinding.BindingAdapter
import az.azerconnect.data.enums.UiState
import az.azerconnect.bakcell.utils.widgets.StateView
import az.azerconnect.bakcell.utils.widgets.ViewState

@BindingAdapter(value = ["app:sv_viewState", "app:isSwipeRefresh"], requireAll = false)
fun StateView.viewState(uiState: UiState?, isSwipeRefresh: Boolean = false) {
    setViewState(
        when (uiState) {
            UiState.SUCCESS -> ViewState.CONTENT
            UiState.LOADING -> if (isSwipeRefresh) getViewState() else ViewState.LOADING
            UiState.ERROR -> ViewState.ERROR
            UiState.EMPTY -> ViewState.EMPTY
            else -> ViewState.CONTENT
        }
    )
}