package az.azerconnect.domain.utils

enum class ResponseStatus(private val status: String?) {
    OK("Ok"),
    FAILED("Failed"),
    NULL_DATA("");
}