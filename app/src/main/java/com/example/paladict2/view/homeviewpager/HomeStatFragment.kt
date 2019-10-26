package com.example.paladict2.view.homeviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.R
import com.example.paladict2.databinding.StatFragmentLayoutBinding
import com.example.paladict2.model.Champion
import com.example.paladict2.model.Match
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.MergedQueueSearchData
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.networking.SessionManager.Companion.isSessionValid
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.utils.KotlinUtils.Companion.formatPaladinsRoleName
import com.example.paladict2.utils.KotlinUtils.Companion.pieChartSetup
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MatchHistoryViewModel
import com.example.paladict2.viewmodel.PlayerViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import com.example.paladict2.viewmodel.factories.MatchHistoryViewModelFactory
import com.example.paladict2.viewmodel.factories.PlayerViewModelFactory
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.stat_fragment_layout.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HomeStatFragment : HomeFragment(), SessionCallback {

    override fun postLogin(isLoggedIn: Boolean) {
        //
    }


    override var title = "HOME"
    private lateinit var matchHistoryViewModel: MatchHistoryViewModel
    private lateinit var selectedPlayerViewModel: PlayerViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var championList: ArrayList<Champion>
    private val chartData = MergedQueueSearchData()
    private val playerData = MergedPlayerSearchData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<StatFragmentLayoutBinding>(
            inflater,
            R.layout.stat_fragment_layout,
            container,
            false
        )
        val view = binding.root
        if (isSessionValid(context!!)) {
            initializeViewModels()
        } else {
            SessionManager.createAndSaveSession(context!!, viewLifecycleOwner, this)
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.matchHistoryViewModel = matchHistoryViewModel
        binding.selectedPlayerViewModel = selectedPlayerViewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRefreshView()
    }

    private fun setupRefreshView() {
        stat_refresh_layout.setOnRefreshListener {
            if(mainViewModel.mChampionsLive.value != null) {
                championList = (mainViewModel.mChampionsLive.value as ArrayList<Champion>?)!!
                updateViewModels()
            }
        }
    }

    private fun initializeViewModels() {
        activity.let {
            matchHistoryViewModel = ViewModelProvider(
                this,
                MatchHistoryViewModelFactory(
                )
            )
                .get(MatchHistoryViewModel::class.java)

            mainViewModel = ViewModelProvider(
                this,
                MainViewModelFactory(
                    activity!!.application
                )
            )
                .get(MainViewModel::class.java)

            selectedPlayerViewModel = ViewModelProvider(
                this,
                PlayerViewModelFactory(
                )
            )
                .get(PlayerViewModel::class.java)
        }
    }

    private fun setupObservers() {
        mainViewModel.mChampionsLive.observe(viewLifecycleOwner, Observer {
            championList = it as ArrayList<Champion>
            updateViewModels()
        })

        matchHistoryViewModel.matches.observe(viewLifecycleOwner, Observer {
            if (stat_refresh_layout.isRefreshing) {
                stat_refresh_layout.isRefreshing = false
            }
            renderData(it)
        })

        selectedPlayerViewModel.player.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun renderData(matchList: List<Match>? = ArrayList()) {
        renderRoleChart(matchList)
    }

    private fun renderRoleChart(matchList: List<Match>? = ArrayList()) {
        user_class_chart.setNoDataTextColor(context!!.getColor(R.color.colorPrimary))
        user_class_chart.setNoDataText(context!!.getString(R.string.no_chart_data))
        val roleHashMap = createRoleHashMap(matchList)
        val matchEntries = arrayListOf<PieEntry>()

        roleHashMap.forEach {
            matchEntries.add(PieEntry(it.value.toFloat(), it.key))
        }

        val roleDataSet = PieDataSet(matchEntries, "")

        roleDataSet.colors = ColorTemplate.JOYFUL_COLORS.toList()
        roleDataSet.setDrawValues(false)

        if (roleHashMap.isNotEmpty()) {
            pieChartSetup(user_class_chart)
            val max = Collections.max(roleHashMap.values)
            roleHashMap.forEach {
                if (it.value == max) {
                    user_class_chart.centerText =
                        context!!.getString(R.string.center_text_role_string, max, it.key)
                }
            }
            user_class_chart.data = PieData(roleDataSet)
        }
        user_class_chart.invalidate()
    }

    private fun createRoleHashMap(matchList: List<Match>? = ArrayList()): HashMap<String, Int> {
        val roleHash = HashMap<String, Int>()
        var championHash = getChampionsForRoles(matchList)
        if (championHash.isNotEmpty()) {
            championHash.forEach {
                for (listedChampion in championList) {
                    if (it.key == listedChampion.name) {
                        val role = formatPaladinsRoleName(listedChampion.roles)
                        roleHash[role] = it.value
                    }
                }
            }
        }
        if(stat_refresh_layout.isRefreshing){
            stat_refresh_layout.isRefreshing = false
        }
        return roleHash
    }

    private fun getChampionsForRoles(matchList: List<Match>? = ArrayList()): HashMap<String, Int> {
        var championHash = HashMap<String, Int>()
        for (match in matchList!!) {
            if (match.champion != null) {
                if (!championHash.containsKey(match.champion!!)) {
                    championHash[match.champion!!] = 1
                } else {
                    var value = championHash[match.champion!!]
                    value = value!!.plus(1)
                    championHash[match.champion!!] = value
                }
            }
        }
        return championHash
    }

    override fun postSessionExecution() {
        initializeViewModels()
    }

    private fun updateViewModels() {
        chartData.playerID = LoginManager.retrievedLoggedInPlayer(context).playerID!!
        chartData.sessionID = retrieveSessionID(context!!)!!

        playerData.playerID = LoginManager.retrievedLoggedInPlayer(context).playerID!!
        playerData.session = retrieveSessionID(context!!)!!

        matchHistoryViewModel.mergedMatchHistoryData.value = chartData
        selectedPlayerViewModel.combinedPlayerSearchData.value = playerData
    }

}
