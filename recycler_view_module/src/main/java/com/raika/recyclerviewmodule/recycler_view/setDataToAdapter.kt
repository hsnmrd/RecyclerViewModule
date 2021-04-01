package com.raika.recyclerviewmodule.recycler_view

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import smartadapter.SmartRecyclerAdapter
import smartadapter.diffutil.extension.diffSwapList

/**
 * update recyclerview with new data
 *
 * @receiver FragmentActivity
 * @param adapter SmartRecyclerAdapter?
 * @param data List<*>?
 * @param onFinished Function0<Unit>?
 */
fun FragmentActivity.setDataToAdapter(adapter: SmartRecyclerAdapter?, data: List<*>?, onFinished: (() -> Unit)? = null) {
    if (data?.isEmpty() == true) {
        adapter?.diffSwapList(lifecycleScope, mutableListOf<Any>(Empty())) {
            onFinished?.invoke()
        }
    } else {
        data?.toList()?.let { dataList ->
            adapter?.diffSwapList(lifecycleScope, dataList) {
                onFinished?.invoke()
            }
        }
    }
}