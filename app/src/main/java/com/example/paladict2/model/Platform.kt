package com.example.paladict2.model

sealed class Platform {
    object PS4 : Platform()
    object Xbox : Platform()
    object Switch : Platform()
    object PC : Platform()

}