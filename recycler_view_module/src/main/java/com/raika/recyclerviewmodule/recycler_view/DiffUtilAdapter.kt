package com.raika.recyclerviewmodule.recycler_view

import smartadapter.diffutil.DiffUtilExtension

object DiffUtilAdapter {
    
    val checkAllFields = object : DiffUtilExtension.DiffPredicate<Any> {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
        
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }

}