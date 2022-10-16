/*
 * Copyright (C) 2022 Nain57
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */
package com.buzbuz.smartautoclicker.overlays.scenario.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper

import com.buzbuz.smartautoclicker.R
import com.buzbuz.smartautoclicker.databinding.ContentEventListBinding
import com.buzbuz.smartautoclicker.domain.Event
import com.buzbuz.smartautoclicker.overlays.base.NavBarDialogContent
import com.buzbuz.smartautoclicker.overlays.base.NavigationRequest
import com.buzbuz.smartautoclicker.overlays.bindings.setEmptyText
import com.buzbuz.smartautoclicker.overlays.bindings.updateState
import com.buzbuz.smartautoclicker.overlays.event.copy.EventCopyDialog
import com.buzbuz.smartautoclicker.overlays.event.EventDialog
import com.buzbuz.smartautoclicker.overlays.scenario.ScenarioDialogViewModel

import kotlinx.coroutines.launch

class EventListContent(private val scenarioId: Long) : NavBarDialogContent() {

    /** View model for this content. */
    private val viewModel: EventListViewModel by lazy {
        ViewModelProvider(this).get(EventListViewModel::class.java)
    }
    /** View model for the container dialog. */
    private val dialogViewModel: ScenarioDialogViewModel by lazy {
        ViewModelProvider(dialogViewModelStoreOwner).get(ScenarioDialogViewModel::class.java)
    }

    /** TouchHelper applied to [eventAdapter] allowing to drag and drop the items. */
    private val itemTouchHelper = ItemTouchHelper(EventReorderTouchHelper())

    /** View binding for all views in this content. */
    private lateinit var viewBinding: ContentEventListBinding
    /** Adapter for the list of events. */
    private lateinit var eventAdapter: EventListAdapter

    override fun onCreateView(container: ViewGroup): ViewGroup {
        viewModel.setScenarioId(scenarioId)

        viewBinding = ContentEventListBinding.inflate(LayoutInflater.from(context), container, false).apply {
            createCopyButtons.apply {
                buttonNew.setOnClickListener { onNewEventButtonPressed() }
                buttonCopy.setOnClickListener { onCopyEventButtonPressed() }
            }
        }

        eventAdapter = EventListAdapter(
            itemClickedListener = ::showEventConfigDialog,
            itemReorderListener = viewModel::updateEventsPriority,
        )

        viewBinding.layoutList.apply {
            setEmptyText(R.string.dialog_event_list_no_events)
            list.apply {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                itemTouchHelper.attachToRecyclerView(this)
                adapter = eventAdapter
            }
        }

        return viewBinding.root
    }

    override fun onViewCreated() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.eventsItems.collect(::updateEventList) }
                launch { viewModel.copyButtonIsVisible.collect(::updateCopyButtonVisibility) }
            }
        }
    }

    private fun onNewEventButtonPressed() {
        showEventConfigDialog(viewModel.getNewEvent(context))
    }

    private fun onCopyEventButtonPressed() {
        showEventCopyDialog()
    }

    private fun updateEventList(newItems: List<Event>?) {
        viewBinding.layoutList.updateState(newItems)
        eventAdapter.submitList(newItems)
    }

    private fun updateCopyButtonVisibility(isVisible: Boolean) {
        viewBinding.createCopyButtons.buttonCopy.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    /** Opens the dialog allowing the user to copy a click. */
    private fun showEventCopyDialog() {
        dialogViewModel.requestSubOverlay(
            NavigationRequest(
                EventCopyDialog(
                    context = context,
                    scenarioId = viewModel.scenarioId.value!!,
                    events = viewModel.eventsItems.value ?: emptyList(),
                    onEventSelected = ::showEventConfigDialog,
                ),
            )
        )
    }

    /** Opens the dialog allowing the user to add a new click. */
    private fun showEventConfigDialog(event: Event) {
        dialogViewModel.requestSubOverlay(
            NavigationRequest(
                overlay = EventDialog(
                    context = context,
                    event = event,
                    onConfigComplete = viewModel::addOrUpdateEvent,
                    onDelete = viewModel::deleteEvent,
                ),
                hideCurrent = true
            )
        )
    }
}