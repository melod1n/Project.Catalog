package com.meloda.projectcatalog.dialog

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meloda.projectcatalog.R
import com.meloda.projectcatalog.adapter.SearchResultsAdapter
import com.meloda.projectcatalog.base.BaseFullScreenDialog
import com.meloda.projectcatalog.databinding.DialogSearchBinding
import com.meloda.projectcatalog.item.SearchResult
import com.meloda.projectcatalog.util.KeyboardUtils
import com.meloda.projectcatalog.view.DividerItemDecoration
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

class SearchDialog : BaseFullScreenDialog(R.layout.dialog_search) {

    private val binding: DialogSearchBinding by viewBinding()
    private lateinit var adapter: SearchResultsAdapter

    private val isLoading = MutableLiveData(false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initRecyclerView()

//        KeyboardUtils.showKeyboard(binding.searchInput)

        binding.searchInput.requestFocus()
        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(binding.searchInput.text.toString().trim())
                return@setOnEditorActionListener true
            }
            false
        }

        binding.cancel.setOnClickListener { dismiss() }

        isLoading.observe({ lifecycleRegistry }) {
            binding.progressBar.isVisible = isLoading.value ?: false
        }
    }

    private fun initAdapter() {
        adapter = SearchResultsAdapter(requireContext(), arrayListOf())
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            addItemDecoration(DividerItemDecoration(requireContext(), 12))
            this.adapter = this@SearchDialog.adapter
        }
    }

    private fun search(query: String) {
        KeyboardUtils.hideKeyboard(binding.searchInput)

        if (query.isEmpty()) return
        fillAdapter()
    }

    private fun fillAdapter() {
        isLoading.value = true

        val kittens = listOf(
            "https://images.pexels.com/photos/691583/pexels-photo-691583.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
            "https://images.pexels.com/photos/2194261/pexels-photo-2194261.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/669015/pexels-photo-669015.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/209037/pexels-photo-209037.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/1317844/pexels-photo-1317844.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/1314550/pexels-photo-1314550.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/69932/tabby-cat-close-up-portrait-69932.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/1183434/pexels-photo-1183434.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/982865/pexels-photo-982865.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/208984/pexels-photo-208984.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/248280/pexels-photo-248280.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/572861/pexels-photo-572861.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/1170986/pexels-photo-1170986.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/1543793/pexels-photo-1543793.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/3777622/pexels-photo-3777622.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/821736/pexels-photo-821736.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
        )

        val items = arrayListOf<SearchResult>()
        for (i in 0..99) {
            items.add(
                SearchResult(
                    i,
                    "Kitten #${i + 1}",
                    Random.nextInt(500, 10000),
                    kittens[Random.nextInt(0, kittens.size)]
                )
            )
        }

        Timer().schedule(1500) {
            requireActivity().runOnUiThread {
                adapter.updateValues(items)

                isLoading.value = false
            }
        }
    }

}