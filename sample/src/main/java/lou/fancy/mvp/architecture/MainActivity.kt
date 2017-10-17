package lou.fancy.mvp.architecture

import android.support.v4.content.ContextCompat
import android.widget.Toast
import lou.fancy.mvp.base.BaseMVPActivity

class MainActivity : BaseMVPActivity<MainContract.Presenter, MainContract.MainView>(), MainContract.MainView {

    override fun showView() {
        Toast.makeText(this, "想啦啦啦", Toast.LENGTH_SHORT).show()
    }

    override var mPresenter: MainContract.Presenter = MainPresenter()

    override fun layoutResId(): Int  = R.layout.activity_main

    override fun afterSetContentView() {
        setupToolBar("这个是标题", titleInCenter = true, hasBackBtn = false, titleColor = ContextCompat.getColor(this, R.color.colorPrimary))
         mPresenter.loadData()
    }
}
