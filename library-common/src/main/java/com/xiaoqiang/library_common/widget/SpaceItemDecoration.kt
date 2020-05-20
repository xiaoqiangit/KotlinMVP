package com.xiaoqiang.library_common.widget

import android.graphics.Canvas
import android.graphics.Rect
import android.support.annotation.IntDef
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author 小强
 *
 * @time 2020/5/13  17:02
 *
 * @desc RecyclerView间距
 *
 */
class SpaceItemDecoration : ItemDecoration {


    companion object {
        const val LINEAR_LAYOUT = 0
        const val GRID_LAYOUT = 1
        const val STAGGERED_GRID_LAYOUT = 2
        const val CUSTOM_LAYOUT = 3 //自定义现行布局
    }


    //限定为LINEAR_LAYOUT,GRID_LAYOUT,STAGGERED_GRID_LAYOUT,CUSTOM_LAYOUT
    @IntDef(LINEAR_LAYOUT, GRID_LAYOUT, STAGGERED_GRID_LAYOUT, CUSTOM_LAYOUT) //表示注解所存活的时间,在运行时,而不会存在. class 文件.
    @Retention(RetentionPolicy.SOURCE)
    annotation class LayoutManager(val type : Int = LINEAR_LAYOUT)


    private var leftRight = 0
    private var topBottom = 0

    /**
     * 头布局个数
     */
    private var headItemCount = 0

    /**
     * 边距
     */
    private var space = 0

    /**
     * 是否包含边距
     */
    private var includeEdge = false

    /**
     * 自定义边距边距 (表示第一个item不需要间距)
     */
    private var customMargin = false

    /**
     * 烈数
     */
    private var spanCount = 0

    @LayoutManager
    private var layoutManager = 0

    private var left = 0
    private var right = 0
    private var top = 0
    private var bottom = 0


    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing
     *
     * @param leftRight     左边间距
     * @param topBottom     顶部间距
     * @param layoutManager layout管理器
     */
    constructor(leftRight : Int, topBottom : Int, headItemCount : Int, @LayoutManager layoutManager : Int) {
        this.leftRight = leftRight
        this.topBottom = topBottom
        this.headItemCount = headItemCount
        this.layoutManager = layoutManager
    }

    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing
     *
     * @param space         全部间距
     * @param includeEdge   是否包含边距
     * @param layoutManager layout管理器
     */
    constructor(space : Int, includeEdge : Boolean, @LayoutManager layoutManager : Int) : this(space, 0, includeEdge, layoutManager)

    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing
     *
     * @param space         全部间距
     * @param headItemCount 是否有头部
     * @param includeEdge   是否包含边距
     * @param layoutManager layout管理器
     */
    constructor(space : Int, headItemCount : Int, includeEdge : Boolean, @LayoutManager layoutManager : Int) {
        this.space = space
        this.headItemCount = headItemCount
        this.includeEdge = includeEdge
        this.layoutManager = layoutManager
    }

    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing 自定义间距
     *
     * @param left            左边间距
     * @param right           右边边距
     * @param top             顶部边距
     * @param bottom          底部边距
     * @param headItemCount   是否有头部
     * @param includeEdge     是否包含边距
     * @param layoutManager   layout管理器
     */
    fun SpaceItemDecoration(left : Int, right : Int, top : Int, bottom : Int, headItemCount : Int, includeEdge : Boolean, @LayoutManager layoutManager : Int) {
        this.left = left
        this.right = right
        this.top = top
        this.bottom = bottom
        this.headItemCount = headItemCount
        this.includeEdge = includeEdge
        this.layoutManager = layoutManager
    }


    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing 自定义间距
     *
     * @param left          左边间距
     * @param right         右边边距
     * @param top           顶部边距
     * @param bottom        底部边距
     * @param customMargin  表示第一个item不需要间距
     * @param layoutManager layout管理器
     */
    constructor(left : Int, right : Int, top : Int, bottom : Int, customMargin : Boolean, @LayoutManager layoutManager : Int) {
        this.left = left
        this.right = right
        this.bottom = bottom
        this.top = top
        this.customMargin = customMargin
        this.layoutManager = layoutManager
    }


    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing
     *
     * @param space         全部间距
     * @param headItemCount 是否有头部
     * @param layoutManager layout管理器
     */
    constructor(space : Int, headItemCount : Int, @LayoutManager layoutManager : Int) : this(space, headItemCount, true, layoutManager)


    /**
     * LinearLayoutManager or GridLayoutManager or StaggeredGridLayoutManager spacing 间距
     *
     * @param space         全部间距
     * @param layoutManager layout管理器
     */

    constructor(space : Int, @LayoutManager layoutManager : Int) : this(space, 0, true, layoutManager)


    override fun onDraw(c : Canvas, parent : RecyclerView, state : RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (layoutManager) {

            CUSTOM_LAYOUT -> setCustomLinearLayoutSpaceItemDecoration(outRect, view, parent, state)

            LINEAR_LAYOUT -> setLinearLayoutSpaceItemDecoration(outRect, view, parent, state)

            GRID_LAYOUT -> {
                val gridLayoutManager = parent.layoutManager as GridLayoutManager?
                //列数
                spanCount = gridLayoutManager!!.spanCount
                setNGridLayoutSpaceItemDecoration(outRect, view, parent, state)
            }

            STAGGERED_GRID_LAYOUT -> {
                val staggeredGridLayoutManager = parent.layoutManager as StaggeredGridLayoutManager?
                //列数
                spanCount = staggeredGridLayoutManager!!.spanCount
                setNGridLayoutSpaceItemDecoration(outRect, view, parent, state)
            }
            else -> {
            }
        }
    }

    /**
     * 自定义线性布局
     */
    private fun setCustomLinearLayoutSpaceItemDecoration(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        //表示第一个item不需要间距
        if (customMargin) {
            outRect.left = left
            outRect.right = right
            outRect.bottom = bottom
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = 0
            } else {
                outRect.top = top
            }
        } else {
            outRect.left = left
            outRect.right = right
            outRect.bottom = bottom
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = top
            } else {
                outRect.top = 0
            }
        }
    }

    /**
     * LinearLayoutManager spacing
     */
    private fun setLinearLayoutSpaceItemDecoration(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
    }

    /**
     * GridLayoutManager or StaggeredGridLayoutManager spacing
     */
    private fun setNGridLayoutSpaceItemDecoration(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) - headItemCount
        if (headItemCount != 0 && position == -headItemCount) {
            return
        }
        val column = position % spanCount
        if (includeEdge) {
            outRect.left = space - column * space / spanCount
            outRect.right = (column + 1) * space / spanCount
            if (position < spanCount) {
                outRect.top = space
            }
            outRect.bottom = space
        } else {
            outRect.left = column * space / spanCount
            outRect.right = space - (column + 1) * space / spanCount
            if (position >= spanCount) {
                outRect.top = space
            }
        }
    }

    /**
     * GridLayoutManager设置间距（此方法最左边和最右边间距为设置的一半）
     */
    private fun setGridLayoutSpaceItemDecoration(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        val layoutManager = parent.layoutManager as GridLayoutManager?
        //判断总的数量是否可以整除
        val totalCount = layoutManager!!.itemCount
        val surplusCount = totalCount % layoutManager.spanCount
        val childPosition = parent.getChildAdapterPosition(view)
        //竖直方向的
        if (layoutManager.orientation == GridLayoutManager.VERTICAL) {
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.spanCount - 1) {
                //后面几项需要bottom
                outRect.bottom = topBottom
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.bottom = topBottom
            }
            //被整除的需要右边
            if ((childPosition + 1 - headItemCount) % layoutManager.spanCount == 0) {
                //加了右边后最后一列的图就非宽度少一个右边距
                //outRect.right = leftRight;
            }
            outRect.top = topBottom
            outRect.left = leftRight / 2
            outRect.right = leftRight / 2
        } else {
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.spanCount - 1) {
                //后面几项需要右边
                outRect.right = leftRight
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.right = leftRight
            }
            //被整除的需要下边
            if ((childPosition + 1) % layoutManager.spanCount == 0) {
                outRect.bottom = topBottom
            }
            outRect.top = topBottom
            outRect.left = leftRight
        }
    }
}