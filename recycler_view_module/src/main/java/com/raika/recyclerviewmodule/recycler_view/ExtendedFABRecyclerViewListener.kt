package com.raika.recyclerviewmodule.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ExtendedFABRecyclerViewListener(
    private val floatingActionButton: ExtendedFloatingActionButton
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy < 0 && !floatingActionButton.isExtended) {
            floatingActionButton.extend()
        
        } else if (dy > 0 && floatingActionButton.isExtended) {
            floatingActionButton.shrink()
        }
    }
}