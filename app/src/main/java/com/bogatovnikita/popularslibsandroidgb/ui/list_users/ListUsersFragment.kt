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

class ListUsersFragment : Fragment() {
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterUserEntity: UserEntityAdapter
    private lateinit var viewModel: ListUsersContract.ViewModel

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
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ListUsersViewModel(app.userEntityRepositorySingleton)
        with(viewModel) {
            Log.e("pie", "initViewModel:ListUsersFragment")
            progressLiveData.observe(viewLifecycleOwner) { showProgress(it) }
            userLiveData.observe(viewLifecycleOwner) { showUsers(it as MutableList<UserEntity>) }
            errorLiveData.observe(viewLifecycleOwner) { showError(it) }
        }
    }

    private fun initView() {
        Log.e("pie", "initView: ListUsersFragment")
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
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

    private fun showUsers(users: MutableList<UserEntity>) {
        Log.e("pie", "showUsers:ListUsersFragment")
        adapterUserEntity.setListUsers(users)
    }

    private fun showError(throwable: Throwable) {
        Log.e("pie", "showError:ListUsersFragment")
        Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        Log.e("pie", "showProgress:ListUsersFragment")
        binding.progressBar.isVisible = inProgress
        binding.recyclerView.isVisible = !inProgress
    }
}