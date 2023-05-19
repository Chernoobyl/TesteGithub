package br.com.chernoobyl.testegithub.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.chernoobyl.testegithub.R
import br.com.chernoobyl.testegithub.databinding.FragmentUserListBinding
import br.com.chernoobyl.testegithub.domain.entities.User
import br.com.chernoobyl.testegithub.presentation.adapters.UserListAdapter
import br.com.chernoobyl.testegithub.presentation.shared.AdapterListener
import br.com.chernoobyl.testegithub.presentation.shared.ErrorInfoBottomSheet
import br.com.chernoobyl.testegithub.presentation.shared.ErrorInfoBottomSheetHandler
import br.com.chernoobyl.testegithub.presentation.viewmodels.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val LOGIN_KEY = "login"

class UserListFragment : Fragment(), AdapterListener<User>, ErrorInfoBottomSheetHandler {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val userListViewModel: UserListViewModel by viewModel()

    private val mAdapter: UserListAdapter by lazy { UserListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        userListViewModel.getAllUsers()
    }

    private fun setupObservers() {
        userListViewModel.userListState.removeObservers(viewLifecycleOwner)
        userListViewModel.userListState.observe(viewLifecycleOwner) { state ->
            state.handle(
                loading = { binding.circularProgressIndicator.show() },
                success = { setupUserListAdapter(it) },
                error = { handleError(it) }
            )
        }
    }

    private fun setupUserListAdapter(userList: List<User>) {
        mAdapter.userList = userList
        binding.userListRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }.also {
            binding.circularProgressIndicator.hide()
        }
    }

    private fun handleError(exception: Exception) {
        val bottomSheet = ErrorInfoBottomSheet.newInstance(exception.message)
        bottomSheet.show(childFragmentManager, ErrorInfoBottomSheet.TAG)
        binding.circularProgressIndicator.hide()
    }

    override fun retry() {
        userListViewModel.getAllUsers()
    }

    override fun performItemClicked(value: User) {
        val bundle = bundleOf(LOGIN_KEY to value.login)
        findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}