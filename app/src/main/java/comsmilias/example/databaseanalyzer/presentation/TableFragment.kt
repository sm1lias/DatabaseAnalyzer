package comsmilias.example.databaseanalyzer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import comsmilias.example.databaseanalyzer.TableFragmentViewModel
import comsmilias.example.databaseanalyzer.databinding.FragmentTableBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val viewModel: TableFragmentViewModel by viewModels()
    private lateinit var tableAdapter: TableAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.users.observe(viewLifecycleOwner){ users ->
            if (users.isNotEmpty()) {
                Timber.i("_____________________ $users")
                tableAdapter.setList(users)
                tableAdapter.notifyDataSetChanged()
            }
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun initRecyclerView(){
        binding.rvTable.layoutManager = LinearLayoutManager(requireContext())
        tableAdapter = TableAdapter()
        binding.rvTable.adapter = tableAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}