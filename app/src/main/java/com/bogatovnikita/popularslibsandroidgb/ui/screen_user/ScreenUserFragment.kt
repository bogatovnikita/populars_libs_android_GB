package com.bogatovnikita.popularslibsandroidgb.ui.screen_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.bogatovnikita.popularslibsandroidgb.LIST_USERS_FROM_USER
import com.bogatovnikita.popularslibsandroidgb.databinding.FragmentScreenUserBinding
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity

class ScreenUserFragment : Fragment() {
    private var _binding: FragmentScreenUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreenUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<UserEntity>(LIST_USERS_FROM_USER)?.let {
            initScreenView(it)
        }
    }

    private fun initScreenView(it: UserEntity) {
        with(binding) {
            iconIv.load(it.avatar)
            nameUserTv.text = it.login
            //descriptionUserTv.setText(it.description)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}