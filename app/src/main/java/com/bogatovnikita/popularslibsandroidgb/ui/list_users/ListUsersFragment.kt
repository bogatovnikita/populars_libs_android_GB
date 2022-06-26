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
import com.bogatovnikita.popularslibsandroidgb.LIST_USERS_FROM_USER
import com.bogatovnikita.popularslibsandroidgb.R
import com.bogatovnikita.popularslibsandroidgb.app
import com.bogatovnikita.popularslibsandroidgb.databinding.FragmentUsersListBinding
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntity
import com.bogatovnikita.popularslibsandroidgb.ui.screen_user.ScreenUserFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ListUsersFragment : Fragment() {
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterUserEntity: UserEntityAdapter
    private lateinit var viewModel: ListUsersContract.ViewModel
    private val viewModelDisposable = CompositeDisposable()

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
        viewModel = ListUsersViewModel(app.userEntityRepositorySingletonRetrofit)
        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.userLiveData.subscribe { showUsers(it as List<UserEntity>) },
            viewModel.errorLiveData.subscribe { showError(it) }
        )
    }

    override fun onDestroy() {
        _binding = null
        viewModelDisposable.dispose()
        super.onDestroy()
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

    private fun showUsers(users: List<UserEntity>) {
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