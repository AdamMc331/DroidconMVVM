package upday.droidconmvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import upday.droidconmvvm.model.Language

class MainActivity : AppCompatActivity() {

    private lateinit var mSubscription: CompositeSubscription
    private lateinit var mViewModel: MainViewModel
    private val mGreetingView: TextView by lazy { findViewById(R.id.greeting) as TextView }
    private val mLanguagesSpinner: Spinner by lazy { findViewById(R.id.languages) as Spinner }
    private var mLanguageSpinnerAdapter: LanguageSpinnerAdapter? = null

    private val viewModel: MainViewModel
        get() = (application as DroidconApplication).viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = viewModel

        mLanguagesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                itemSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unBind()
    }

    private fun bind() {
        mSubscription = CompositeSubscription()

        mSubscription.add(mViewModel.greeting
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.setGreeting(it) }))

        mSubscription.add(mViewModel.supportedLanguages
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.setLanguages(it) }))
    }

    private fun unBind() {
        mSubscription.unsubscribe()
    }

    private fun setGreeting(greeting: String) {
        mGreetingView.text = greeting
    }

    private fun setLanguages(languages: List<Language>) {
        mLanguageSpinnerAdapter = LanguageSpinnerAdapter(this, R.layout.language_item, languages)
        mLanguagesSpinner.adapter = mLanguageSpinnerAdapter
    }

    private fun itemSelected(position: Int) {
        val languageSelected = mLanguageSpinnerAdapter?.getItem(position)
        mViewModel.languageSelected(languageSelected)
    }
}
