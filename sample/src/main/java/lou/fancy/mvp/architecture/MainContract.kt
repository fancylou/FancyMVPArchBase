package lou.fancy.mvp.architecture

import lou.fancy.mvp.base.BasePresenter
import lou.fancy.mvp.base.BaseView

/**
 * Created by fancylou on 10/17/17.
 */


object  MainContract {
    interface MainView: BaseView {
        fun showView()
    }
    interface Presenter: BasePresenter<MainView>{
        fun  loadData()
    }
}