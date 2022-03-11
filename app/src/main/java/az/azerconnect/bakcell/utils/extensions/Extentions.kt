
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import az.azerconnect.data.enums.UiState
import az.azerconnect.data.models.dto.ErrorDialogDto
import az.azerconnect.data.api.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow


fun <T> Resource<T>.asUiState(checkEmptyList: Boolean = false): UiState {
    return when (this) {
        is Resource.Success -> if (checkEmptyList && this.data is List<*> && (this.data as List<*>).isNullOrEmpty()) UiState.EMPTY else UiState.SUCCESS
        is Resource.Error -> UiState.ERROR
    }
}

fun <T> Resource<T>.asErrorDialogModel(): ErrorDialogDto? {
    return when (this) {
        is Resource.Error -> ErrorDialogDto(message = this.message)
        else -> null
    }
}

fun Drawable?.setColorFilter(@ColorInt color: Int) {
    this?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}

fun List<*>.toArrayList(): ArrayList<*> {
    return ArrayList(this)
}

suspend fun <T> MutableStateFlow<List<T>>.addItem(index: Int? = null, item: T) {
    val list = value.toMutableList()
    list.add(index ?: list.size, item)

    emit(list)
}

suspend fun <T> MutableStateFlow<List<T>>.removeItem(item: T) {
    val list = value.toMutableList()

    if (list.contains(item)) {
        list.remove(item)

        emit(list.toList())
    }
}

suspend fun <T> MutableStateFlow<List<T>>.replaceItem(oldItem: T, newItem: T) {
    val list = value.toMutableList()
    val index = list.indexOf(oldItem)

    index.let {
        if (index >= 0) {

            list.removeAt(it)
            list.add(it, newItem)

            emit(list.toList())
        }
    }
}

fun CombinedLoadStates.asUiState(itemCount: Int) = when (source.refresh) {
    is LoadState.Loading -> UiState.LOADING
    is LoadState.NotLoading -> {
        if (itemCount <= 0 && append.endOfPaginationReached)
            UiState.EMPTY
        else
            UiState.SUCCESS

    }
    else -> UiState.ERROR
}