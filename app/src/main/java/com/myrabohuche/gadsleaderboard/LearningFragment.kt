package com.myrabohuche.gadsleaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.myrabohuche.gadsleaderboard.databinding.LearningFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LearningFragment : Fragment() {

    companion object {
        fun newInstance() = LearningFragment()
    }
    @ExperimentalCoroutinesApi
    @Inject lateinit var viewModel: LearningViewModel
    private lateinit var binding: LearningFragmentBinding

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=LearningFragmentBinding.inflate(inflater)

        lifecycleScope.launchWhenStarted {
            viewModel.load().collect {
                when (it) {

                    is State.Loading -> {
                        Toast.makeText(context,"Loading",Toast.LENGTH_LONG).show()
                        //binding.groupLoading.visibility = View.VISIBLE

                    }

                    is State.Success -> {
                        it.apply {
                            Toast.makeText(context,"$it",Toast.LENGTH_LONG).show()
                            //binding.groupLoading.visibility = View.GONE
                            //adapter.submitList(it.data)
                        }
                    }

                    is State.Failed ->{
                        //binding.cloudId.visibility = View.VISIBLE
                        Toast.makeText(context,"Failed${it.message}",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        return binding.root
    }

}