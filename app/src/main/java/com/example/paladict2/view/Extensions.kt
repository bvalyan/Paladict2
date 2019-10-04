package com.example.paladict2.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.addFragmentWithBackstack(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransactionWithBackstack { add(frameId, fragment) }
}

inline fun FragmentManager.inTransactionWithBackstack(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().addToBackStack("added").commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}

