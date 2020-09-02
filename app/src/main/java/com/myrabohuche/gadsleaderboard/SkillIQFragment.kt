package com.myrabohuche.gadsleaderboard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SkillIQFragment : Fragment() {

    companion object {
        fun newInstance() = SkillIQFragment()
    }

    private lateinit var viewModel: SkillIQViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.skill_i_q_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SkillIQViewModel::class.java)
        // TODO: Use the ViewModel
    }

}