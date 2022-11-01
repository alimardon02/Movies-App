package uz.gita.moviesapp.utils.extension

fun usingJavaStringFormat(input: Double, scale: Int) = String.format("%.${scale}f", input)