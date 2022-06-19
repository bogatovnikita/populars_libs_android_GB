package com.bogatovnikita.popularslibsandroidgb.ui.list_users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bogatovnikita.popularslibsandroidgb.R
import com.bogatovnikita.popularslibsandroidgb.app
import com.bogatovnikita.popularslibsandroidgb.databinding.FragmentUsersListBinding
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.ui.screen_user.ScreenUserFragment
import com.bogatovnikita.popularslibsandroidgb.utils.LIST_USERS_FROM_USER

class ListUsersFragment : Fragment(), ListUsersContract.View {
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterUserEntity: UserEntityAdapter
    private lateinit var presenter: ListUsersContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("pie", "onViewCreated: fragment")
        initView()
        presenter = ListUsersPresenter(app.userEntityRepositorySingleton)
        presenter.attach(this)
    }

    private fun initView() {
        binding.refreshButton.setOnClickListener {
            presenter.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun initRecyclerView() {
        adapterUserEntity = UserEntityAdapter {
            Log.e("pie", "ListUsersFragment:initRecyclerView $it")
            openUserScreenInfo(it)
        }
        binding.recyclerView.adapter = adapterUserEntity
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun openUserScreenInfo(user: UserEntity) {
        val args = Bundle()
        args.putParcelable(LIST_USERS_FROM_USER, user)
        val newFragment = ScreenUserFragment()
        newFragment.arguments = args
        Log.e("pie", "ListUsersFragment:openUserScreenInfo $args")

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_container_frame_layout, newFragment
            )
            .addToBackStack("")
            .commit()
    }

    override fun showUsers(users: MutableList<UserEntity>) {
        adapterUserEntity.setListUsers(users)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.recyclerView.isVisible = !inProgress
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
        presenter.detach()
    }
}