package uz.gita.moviesapp.data.sources.common


sealed class DataWrapper<T> {
    class Empty<T> : DataWrapper<T>()
    class Loading<T> : DataWrapper<T>()
    data class Success<T>(val data: T) : DataWrapper<T>()
    data class Error<T>(val error: Exception) : DataWrapper<T>()
}
