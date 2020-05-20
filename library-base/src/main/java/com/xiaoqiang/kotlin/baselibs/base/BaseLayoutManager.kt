package com.xiaoqiang.kotlin.baselibs.base

import android.content.Context
import android.support.annotation.IntDef
import android.support.v7.widget.*
import com.xiaoqiang.library_common.utils.CommonUtil
import com.xiaoqiang.library_common.widget.SpaceItemDecoration
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author 小强
 *
 * @time 2020/5/14  9:11
 *
 * @ 布局管理器基类
 *
 */
class BaseLayoutManager {

    companion object {
        const val HORIZONTAL = 0 //横向
        const val VERTICAL = 1 //众向

        const val LINEAR_LAYOUT = 0          //线性布局
        const val GRID_LAYOUT = 1            //网格布局
        const val STAGGERED_GRID_LAYOUT = 2  //瀑布流布局
        const val CUSTOM_LAYOUT = 3          //自定义布局

    }


    /**
     * 布局管理器选择
     */
    @IntDef(LINEAR_LAYOUT, GRID_LAYOUT, STAGGERED_GRID_LAYOUT, CUSTOM_LAYOUT)
    @Retention(RetentionPolicy.SOURCE)    //表示注解所存活的时间,在运行时,而不会存在. class 文件.
    annotation class LayoutManager(val type : Int = LINEAR_LAYOUT)

    /**
     * 方向选择
     */
    @IntDef(HORIZONTAL, VERTICAL)
    @Retention(RetentionPolicy.SOURCE)
    annotation class orientation(val type : Int = VERTICAL)

    class Builder(private val mContext : Context?, private val mRecyclerView : RecyclerView?) {


        //边距
        private var mSpace = 10f

        //自定义边距边距 (表示第一个item不需要间距)
        private var mCustomMargin = false

        private var mLeft = 10f

        private var mRight = 10f

        private var mTop = 10f

        private var mBottom = 10f

        @LayoutManager
        private var mLayoutManager = LINEAR_LAYOUT


        @orientation
        private var mOrientation = VERTICAL

        //表示有几列
        private var mSpanCount = 2

        //是否改变布局
        private var mIsReverseLayout = false

        //是否包含边距
        private var mIncludeEdge = true


        /**
         * 设置边距
         */
        fun setSpace(space : Float) : Builder {
            mSpace = space
            return this
        }


        /**
         * 设置左边距
         */
        fun setLeft(left : Float) : Builder {
            mLeft = left
            return this
        }

        /**
         * 设置右边距
         */
        fun setRight(right : Float) : Builder {
            mRight = right
            return this
        }

        /**
         * 设置顶部边距
         */
        fun setTop(top : Float) : Builder {
            mTop = top
            return this
        }

        /**
         * 设置底部部边距
         */
        fun setBottom(bottom : Float) : Builder {
            mBottom = bottom
            return this
        }

        /**
         * 设置布局管理器
         */
        fun setLayoutManager(@LayoutManager layoutManager : Int) : Builder {
            mLayoutManager = layoutManager
            return this
        }

        /**
         * 设置方向
         */
        fun setOrientation(@orientation orientation : Int) : Builder {
            mOrientation = orientation
            return this
        }

        /**
         * 设置第一个item不需要间距(适用于自定义)
         */
        fun setCustomMargin(customMargin : Boolean) : Builder {
            mCustomMargin = customMargin
            return this
        }

        /**
         * 设置列数
         */
        fun setSpanCount(spanCount : Int) : Builder {
            mSpanCount = spanCount
            return this
        }

        /**
         * 是否改变布局
         */
        fun setIsReverseLayout(isReverseLayout : Boolean) : Builder {
            mIsReverseLayout = isReverseLayout
            return this
        }

        /**
         * 是否改变布局
         */
        fun setIsIncludeEdge(includeEdge : Boolean) : Builder {
            mIncludeEdge = includeEdge
            return this
        }


        private val linearLayoutManager : LinearLayoutManager by lazy {
            LinearLayoutManager(mContext, mOrientation, mIsReverseLayout)
        }


        private val gridLayoutManager : GridLayoutManager by lazy {
            GridLayoutManager(mContext, mSpanCount, mOrientation, mIsReverseLayout)
        }


        private val staggeredGridLayoutManager : StaggeredGridLayoutManager by lazy {
            StaggeredGridLayoutManager(mSpanCount, mOrientation)
        }


        /**
         * 每一项线性线布局的横线(RecyclerView Divider)
         */
        private val linearLayoutItemDecoration by lazy {
            mContext?.run {
                val space = CommonUtil.dp2px(this, mSpace)
                SpaceItemDecoration(space, mIncludeEdge, SpaceItemDecoration.LINEAR_LAYOUT)
            }
        }


        /**
         * 每一项网格布局的横线(RecyclerView Divider)
         */
        private val gridLayoutItemDecoration by lazy {

            mContext?.run {
                val space = CommonUtil.dp2px(this, mSpace)
                SpaceItemDecoration(space, mIncludeEdge, SpaceItemDecoration.GRID_LAYOUT)
            }
        }

        /**
         * 每一项瀑布流布局的横线(RecyclerView Divider)
         */
        private val staggeredGridItemDecoration by lazy {

            mContext?.run {
                val space = CommonUtil.dp2px(this, mSpace)
                SpaceItemDecoration(space, mIncludeEdge, SpaceItemDecoration.STAGGERED_GRID_LAYOUT)
            }
        }


        /**
         * 自定义瀑布流布局的横线(RecyclerView Divider)
         */
        private val customStaggeredGridItemDecoration by lazy {

            mContext?.run {
                val left = CommonUtil.dp2px(this, mLeft)
                val right = CommonUtil.dp2px(this, mRight)
                val top = CommonUtil.dp2px(this, mTop)
                val bottom = CommonUtil.dp2px(this, mBottom)
                SpaceItemDecoration(left, right, top, bottom, mCustomMargin, SpaceItemDecoration.CUSTOM_LAYOUT)
            }
        }


        fun build() : BaseLayoutManager {


            when (mLayoutManager) {


                LINEAR_LAYOUT -> {

                    mRecyclerView?.run {

                        itemAnimator = DefaultItemAnimator()
                        this.layoutManager = linearLayoutManager
                        linearLayoutItemDecoration?.let { addItemDecoration(it) }

                    }
                }
            }

            when (mLayoutManager) {

                //线性布局
                LINEAR_LAYOUT -> {


                }

                //网格布局
                GRID_LAYOUT -> {
                    mRecyclerView?.run {
                        val linearLayoutManager : GridLayoutManager by lazy {
                            GridLayoutManager(mContext, 3)
                        }

                        this.layoutManager = gridLayoutManager
                        itemAnimator = DefaultItemAnimator()
                        gridLayoutItemDecoration?.let { addItemDecoration(it) }

                    }
                }


                //瀑布流布局
                STAGGERED_GRID_LAYOUT -> {
                    mRecyclerView?.run {
                        this.layoutManager = staggeredGridLayoutManager
                        itemAnimator = DefaultItemAnimator()
                        staggeredGridItemDecoration?.let { addItemDecoration(it) }

                    }
                }

                //自定义布局
                CUSTOM_LAYOUT -> {
                    mRecyclerView?.run {
                        this.layoutManager = staggeredGridLayoutManager
                        itemAnimator = DefaultItemAnimator()
                        customStaggeredGridItemDecoration?.let { addItemDecoration(it) }

                    }
                }

            }

            return BaseLayoutManager()
        }
    }
}