package com.raika.recyclerviewmodule.recycler_view

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.raika.popupmodule.popup.PopupMenuRoot

private fun manageTouch(e: MotionEvent, recyclerView: RecyclerView, touchListener: (view: View, position: Int) -> Unit) {
    when (e.action) {
        MotionEvent.ACTION_DOWN -> {
            recyclerView.findChildViewUnder(e.x, e.y)?.let {
                val position = recyclerView.getChildAdapterPosition(it)
                touchListener(it, position)
            }
        }
    }
}

fun RecyclerView.touchItemListener(touchListener: (view: View, position: Int) -> Unit) {
    
    /** listen to item touch and return lambda when item touched */
    this.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
            manageTouch(motionEvent, recyclerView) { view, position ->
                touchListener(view, position)
            }
            return super.onInterceptTouchEvent(recyclerView, motionEvent)
        }
    })
    
    /** hide popup when recyclerview scrolled */
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> PopupMenuRoot.popup?.dismiss()
                RecyclerView.SCROLL_STATE_SETTLING -> PopupMenuRoot.popup?.dismiss()
            }
        }
    })
    
}

fun RecyclerView.fabVisibility(layoutManager: LinearLayoutManager, fabTarget: FloatingActionButton, indexToShow: Int = 4) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val totalItemCount: Int = layoutManager.itemCount
            val firstVisible: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
            val firstHasBeenReached = firstVisible >= indexToShow
            if (totalItemCount > 0 && firstHasBeenReached) {
                fabTarget.show()
            } else {
                fabTarget.hide()
            }
        }
    })
    
    fabTarget.setOnClickListener {
        this.smoothScrollToPosition(0)
    }
}

fun RecyclerView.fabShrinkAndExtendRecyclerView(floatingActionButton: ExtendedFloatingActionButton) {
    this.addOnScrollListener(ExtendedFABRecyclerViewListener(floatingActionButton))
}