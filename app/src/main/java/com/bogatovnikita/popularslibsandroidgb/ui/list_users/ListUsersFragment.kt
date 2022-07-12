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
import com.bogatovnikita.popularslibsandroidgb.domain.UserEntityRepository
import com.bogatovnikita.popularslibsandroidgb.ui.screen_user.ScreenUserFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ListUsersFragment : Fragment() {
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterUserEntity: UserEntityAdapter
    private val viewModelDisposable = CompositeDisposable()

    @Inject
    lateinit var userEntityRepositorySingletonRetrofit: UserEntityRepository

    private val viewModel: ListUsersViewModel by lazy {
        ListUsersViewModel(userEntityRepositorySingletonRetrofit)
    }

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
        Log.d("pie", "ListUsersFragment: onViewCreated")
        if (savedInstanceState == null) {
            Log.d("pie", "ListUsersFragment:savedInstanceState $savedInstanceState")
            showProgress(false)
            app.appComponent.inject(this)
            initView()
            initViewModel()
        }
    }

    private fun initViewModel() {
        Log.e("pie", "ListUsersFragment:initViewModel")
        viewModelDisposable.addAll(
            viewModel.progressObservable.subscribe { showProgress(it) },
            viewModel.userObservable.subscribe { showUsers(it as List<UserEntity>) },
            viewModel.errorObservable.subscribe { showError(it) }
        )
    }

    override fun onDestroy() {
        _binding = null
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun initView() {
        Log.e("pie", "ListUsersFragment:initView")
        binding.refreshButton.setOnClickListener {
            Log.e("pie", "ListUsersFragment:initView setOnClickListener")
            viewModel.onRefresh()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.e("pie", "ListUsersFragment:initRecyclerView")
        adapterUserEntity = UserEntityAdapter {
            Log.e("pie", "ListUsersFragment:initRecyclerView adapter $it")
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
        Log.e("pie", "ListUsersFragment:showUsers")
        adapterUserEntity.setListUsers(users)
    }

    private fun showError(throwable: Throwable) {
        Log.e("pie", "ListUsersFragment:showError")
        Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        Log.e("pie", "ListUsersFragment:showProgress")
        binding.progressBar.isVisible = inProgress
        binding.recyclerView.isVisible = !inProgress
    }
}