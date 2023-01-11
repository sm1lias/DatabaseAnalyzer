package comsmilias.example.databaseanalyzer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.queryTextChanges
import comsmilias.example.databaseanalyzer.TableFragmentViewModel
import comsmilias.example.databaseanalyzer.databinding.FragmentTableBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val viewModel: TableFragmentViewModel by viewModels()
    private lateinit var tableAdapter: TableAdapter
    private val compositeDisposable = CompositeDisposable()

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
        viewModel.users.observe(viewLifecycleOwner) { users ->
            if (users.isNotEmpty()) {
                Timber.i("_____________________ $users")
                tableAdapter.setList(users)
                tableAdapter.notifyDataSetChanged()
            }
        }

        binding.btnSearch
            .queryTextChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { compositeDisposable.add(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { charSequence ->
                viewModel.users.value?.let {
                    tableAdapter.setList(it)
                }
                tableAdapter.setList(
                    tableAdapter.getList()
                        .filter {
                            it.location.area.lowercase()
                                .contains(charSequence.toString().lowercase())
                        }
                )
                tableAdapter.notifyDataSetChanged()
            }
    }

    private fun initRecyclerView() {
        binding.rvTable.layoutManager = LinearLayoutManager(requireContext())
        tableAdapter = TableAdapter()
        binding.rvTable.adapter = tableAdapter
        binding.rvTable.addItemDecoration(
            DividerItemDecoration(
                binding.rvTable.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable.clear()
    }
}