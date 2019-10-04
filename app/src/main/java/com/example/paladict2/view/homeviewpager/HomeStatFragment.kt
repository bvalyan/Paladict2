package com.example.paladict2.view.homeviewpager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.Constants.Companion.ROLES
import com.example.paladict2.R
import com.example.paladict2.model.Champion
import com.example.paladict2.model.Match
import com.example.paladict2.model.MergedQueueSearchData
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MatchHistoryViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import com.example.paladict2.viewmodel.factories.MatchHistoryViewModelFactory
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.stat_fragment_layout.*

class HomeStatFragment : HomeFragment(), SessionCallback {

    override fun postLogin(isLoggedIn: Boolean) {
        //
    }

    override var title = "HOME"
    private lateinit var matchHistoryViewModel: MatchHistoryViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var championList: ArrayList<Champion>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stat_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModels()

        val chartData = MergedQueueSearchData()

        chartData.playerID = LoginManager.retrievedLoggedInPlayer(context).playerID!!
        chartData.sessionID = retrieveSessionID(context!!)!!

        matchHistoryViewModel.mergedMatchHistoryData.value = chartData
    }

    private fun initializeViewModels() {
        matchHistoryViewModel = ViewModelProviders.of(
            this,
            MatchHistoryViewModelFactory(
            )
        )
            .get(MatchHistoryViewModel::class.java)

        mainViewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(
                retrieveSessionID(
                    context!!
                ) as String
            )
        )
            .get(MainViewModel::class.java)

        matchHistoryViewModel.matches.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })

        mainViewModel.champions.observe(viewLifecycleOwner, Observer {
            championList = it as ArrayList<Champion>
        })
    }

    private fun renderData(matchList: List<Match>? = ArrayList()) {
        renderRoleChart(matchList)
    }

    private fun renderRoleChart(matchList: List<Match>? = ArrayList()) {
        
        user_class_chart.setNoDataTextColor(context!!.getColor(R.color.colorAccent))
        var roleHashMap = createRoleHashMap(matchList)
        var matchEntries = arrayListOf<PieEntry>()

        roleHashMap.forEach {
            matchEntries.add(PieEntry(it.value.toFloat(), it.key))
        }

        var roleDataSet = PieDataSet(matchEntries, ROLES)

        roleDataSet.colors = ColorTemplate.JOYFUL_COLORS.toList()
        roleDataSet.setDrawValues(false)



        if(roleHashMap.isNotEmpty()) {
            chartSetup()
            user_class_chart.data = PieData(roleDataSet)
        }
        user_class_chart.invalidate()
    }

    private fun chartSetup() {
        user_class_chart.isDrawHoleEnabled = true
        user_class_chart.holeRadius = 85f
        user_class_chart.setHoleColor(Color.TRANSPARENT)
        user_class_chart.legend.textColor = Color.WHITE
        user_class_chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        user_class_chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        user_class_chart.legend.orientation = Legend.LegendOrientation.VERTICAL
        user_class_chart.setDrawEntryLabels(false)
        user_class_chart.description.isEnabled = false
        user_class_chart.transparentCircleRadius = 50f
        user_class_chart.offsetLeftAndRight(-100)
    }

    private fun createRoleHashMap(matchList: List<Match>? = ArrayList()): HashMap<String, Int> {
        val roleHash = HashMap<String, Int>()
        var championHash = getChampionsForRoles(matchList)
        if (championHash.isNotEmpty()) {
            championHash.forEach {
                for (listedChampion in championList) {
                    if (it.key == listedChampion.name) {
                        roleHash[listedChampion.roles!!] = it.value
                    }
                }
            }
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
        //TODO: session control
    }

}
