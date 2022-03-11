package az.azerconnect.data.models.dto


class ErrorDialogDto(
    val title: String? = "",
    var message: String? = "",
    var cancelable: Boolean = true,
    var showDialog: Boolean = true,
    var showNoInternetDialog: Boolean = true,
    var positiveButton: ButtonDto = ButtonDto(),
    var negativeButton: ButtonDto = ButtonDto(text = 0),
)