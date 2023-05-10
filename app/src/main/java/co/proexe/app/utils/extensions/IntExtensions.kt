package co.proexe.app.utils.extensions

fun Int.toTwoDigitString(): String{
    return String.format("%02d",this)
}