

package com.onion.multview

import android.content.Context
import android.net.ConnectivityManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.collection.ArrayMap

/**
 * 功能强大的多状态布局
 *
 *
 * 全局配置
 * 单例配置
 * 不支持RecyclerView
 * 可以代码替换或者布局使用
 * 无网络情况下showLoading显示错误布局, 有网则显示加载中布局
 */
class MultView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    companion object{

    }

    private var empty_img: Int = R.mipmap.empty
    private var empty_text: String? = "暂无数据"

    private var contentId: Int = 1
    private val contentViews = ArrayMap<Int, View>()

    //只显示一次没有网络布局
    var showNetErrorFirst = false

    var errorCount = 0

    var isShowLoading = true

    var needLoading = true

    @LayoutRes
    var errorLayout: Int = R.layout.widget_error_page
        set(value) {
            if (field != value) {
                remove(field)
                field = value
            }
        }

    @LayoutRes
    var networkLayout: Int = R.layout.widget_nonetwork_page
        set(value) {
            if (field != value) {
                remove(field)
                field = value
            }
        }

    @LayoutRes
    var emptyLayout: Int = R.layout.widget_empty_page
        set(value) {
            if (field != value) {
                remove(field)
                field = value
            }
        }

    @LayoutRes
    var loadingLayout: Int = R.layout.widget_loading_page
        set(value) {
            if (field != value) {
                remove(field)
                field = value
            }
        }

    private var onEmpty: (View.() -> Unit)? = null
    private var onError: (View.() -> Unit)? = null
    private var onNetwork: (View.() -> Unit)? = null
    private var onLoading: (View.() -> Unit)? = null

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultView)
        try {
            emptyLayout = attributes.getResourceId(R.styleable.MultView_empty_layout, R.layout.widget_empty_page)
            loadingLayout = attributes.getResourceId(R.styleable.MultView_loading_layout, R.layout.widget_loading_page)
            empty_img = attributes.getResourceId(R.styleable.MultView_empty_img,R.mipmap.empty)
            showNetErrorFirst = attributes.getBoolean(R.styleable.MultView_showNetErrorFirst,false)
            empty_text = attributes.getString(R.styleable.MultView_empty_text)
            errorLayout = attributes.getResourceId(R.styleable.MultView_error_layout, R.layout.widget_error_page)
            networkLayout = attributes.getResourceId(R.styleable.MultView_network_layout, R.layout.widget_nonetwork_page)
            isShowLoading = attributes.getBoolean(R.styleable.MultView_isShowLoding,true)
        } finally {
            attributes.recycle()
        }
    }

    /**
     * 是否加载loading
     */
    fun isShowLoding():Boolean = isShowLoading

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 1 || childCount == 0) {
            throw UnsupportedOperationException("Must contain child view of one")
        }
        if (contentViews.size == 0) {
            val view = getChildAt(0)
            setContentView(view)
        }

        if(isShowLoading){
            showLoading()
        }

    }

    fun onEmpty(block: View.() -> Unit) {
        onEmpty = block
    }

    fun onLoading(block: View.() -> Unit) {
        onLoading = block
    }

    fun onError(block: View.() -> Unit) {
        onError = block
    }

    fun onNetwork(block: View.() -> Unit){
        onNetwork = block
    }

    fun onBoth(block: View.() -> Unit){
        onEmpty = block
        onError = block
        onNetwork = block
    }

    /**
     * 有网则显示加载中, 无网络直接显示错误
     */
    fun showLoading() {
        if (context.isNetworkConnected()) {
            if (loadingLayout == NO_ID) {
                loadingLayout = StateConfig.loadingLayout
            }

            if (loadingLayout != NO_ID) {
                show(loadingLayout)
            }
        } else {
            showNetwork()
        }
    }

    /**
     * 判断是否有网络连接
     */
    private fun Context.isNetworkConnected(): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    fun showEmpty() {
        if (emptyLayout == NO_ID) {
            emptyLayout = StateConfig.emptyLayout
        }
        if (emptyLayout != NO_ID) {
            show(emptyLayout)
        }
    }

    fun showError() {
        if (errorLayout == NO_ID) {
            errorLayout = StateConfig.errorLayout
        }
        if (errorLayout != NO_ID) {
            show(errorLayout)
        }
    }

    fun showNetwork() {
        if(showNetErrorFirst){

            if(errorCount > 0){
                //已经显示过一次error了 不会在去显示
                return
            }
            if (networkLayout == NO_ID) {
                networkLayout = StateConfig.networkLayout
            }
            if (errorLayout != NO_ID) {
                show(networkLayout)
            }
            errorCount++
        }else{
            if (networkLayout == NO_ID) {
                networkLayout = StateConfig.networkLayout
            }
            if (errorLayout != NO_ID) {
                show(networkLayout)
            }
        }
    }

    fun showContent() {
        show(contentId)
    }

    /**
     * 显示视图
     */
    private fun show(layoutId: Int) {
        for (view in contentViews.values) {
            view.visibility = View.GONE
        }

        try {
            layout(layoutId)!!.visibility = View.VISIBLE
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    private fun remove(layoutId: Int?) {
        if (contentViews.containsKey(layoutId)) {
            val vg = contentViews.remove(layoutId)
            removeView(vg)
        }
    }

    @Throws(NullPointerException::class)
    private fun layout(@LayoutRes layoutId: Int): View? {
        if (contentViews.containsKey(layoutId)) {
            return contentViews[layoutId]
        }

        val view = LayoutInflater.from(context).inflate(layoutId, this, false)

        addView(view)
        contentViews[layoutId] = view

        when (layoutId) {
            emptyLayout -> {
                //设置空数据
                view.setOnClickListener {
                    onEmpty?.let { empty -> empty() }
                }
//                if (onEmpty == null) {
//                    StateConfig.onEmpty?.let {
//                        onEmpty = it
//                    }
//                }
//                onEmpty?.invoke(view)
            }
            errorLayout -> {
                view.setOnClickListener {
                    onError?.let { error -> error() }
                }
//                if (onError == null) {
//                    StateConfig.onError?.let {
//                        onError = it
//                    }
//                }
//                onError?.invoke(view)
            }
            loadingLayout -> {
                if (onLoading == null) {
                    StateConfig.onLoading?.let {
                        onLoading = it
                    }
                }
                onLoading?.invoke(view)
            }

            networkLayout -> {
                view.setOnClickListener {
                    onNetwork?.let { network -> network() }
                }
            }
        }
        return view
    }

    internal fun setContentView(view: View) {
        contentViews[contentId] = view
    }
}

/**
 * 全局的单列多状态布局配置
 */
object StateConfig {

    @LayoutRes
    var errorLayout = R.layout.widget_error_page
    @LayoutRes
    var emptyLayout = R.layout.widget_empty_page
    @LayoutRes
    var networkLayout = R.layout.widget_nonetwork_page
    @LayoutRes
    var loadingLayout = R.layout.widget_loading_page

    internal var onEmpty: (View.() -> Unit)? = null
    internal var onError: (View.() -> Unit)? = null
    internal var onNetwork: (View.() -> Unit)? = null
    internal var onLoading: (View.() -> Unit)? = null

    fun onEmpty(block: View.() -> Unit) {
        onEmpty = block
    }

    fun onLoading(block: View.() -> Unit) {
        onLoading = block
    }

    fun onError(block: View.() -> Unit) {
        onError = block
    }

    fun onNetwork(block: View.() -> Unit){
        onNetwork = block
    }
}
