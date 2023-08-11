/*
 * Copyright (C) 2023 Kevin Buzeau
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.buzbuz.smartautoclicker.feature.tutorial.data.tutorials

import com.buzbuz.smartautoclicker.core.ui.monitoring.MonitoredViewType
import com.buzbuz.smartautoclicker.feature.tutorial.R
import com.buzbuz.smartautoclicker.feature.tutorial.data.StepEndCondition
import com.buzbuz.smartautoclicker.feature.tutorial.data.StepStartCondition
import com.buzbuz.smartautoclicker.feature.tutorial.data.TutorialData
import com.buzbuz.smartautoclicker.feature.tutorial.data.TutorialInfo
import com.buzbuz.smartautoclicker.feature.tutorial.data.TutorialStepData
import com.buzbuz.smartautoclicker.feature.tutorial.data.game.TutorialGameData
import com.buzbuz.smartautoclicker.feature.tutorial.data.game.rules.OneStillTargetRules

internal val oneStillTargetTutorialInfo: TutorialInfo =
    TutorialInfo(
        nameResId = R.string.item_title_game_1,
        descResId = R.string.item_desc_game_1,
    )

internal fun newOneStillTargetTutorial(): TutorialData =
    TutorialData(
        info = oneStillTargetTutorialInfo,
        game = TutorialGameData(
            instructionsResId = R.string.message_tutorial_instructions_1,
            gameRules = OneStillTargetRules(10),
        ),
        steps = listOf(
            // Start screen, before first play
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.Immediate,
                stepEndCondition = StepEndCondition.NextButton,
            ),
            // First play lost, open edit scenario
            TutorialStepData.ChangeFloatingUiVisibility(
                stepStartCondition = StepStartCondition.GameLost,
                isVisible = true,
            ),
            // First play lost, open edit scenario
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.Immediate,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.FLOATING_MENU_BUTTON_CONFIG,
                ),
            ),
            // Create a new event
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.SCENARIO_DIALOG_BUTTON_CREATE_EVENT,
                ),
            ),
            // Select condition tab
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.EVENT_DIALOG_TAB_BUTTON_CONDITIONS,
                ),
            ),
            // Create a new condition
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.Immediate,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.EVENT_DIALOG_BUTTON_CREATE_CONDITION,
                ),
            ),
            // Take a screenshot
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.NextButton,
            ),
            // Adjust screenshot
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.MonitoredViewClicked(
                    MonitoredViewType.CONDITION_CAPTURE_BUTTON_CAPTURE,
                ),
                stepEndCondition = StepEndCondition.NextButton,
            ),
            // Save condition
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.NextButton,
            ),
            // Select action tab
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.EVENT_DIALOG_TAB_BUTTON_ACTIONS,
                ),
            ),
            // Create a new action
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.Immediate,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.EVENT_DIALOG_BUTTON_CREATE_ACTION,
                ),
            ),
            // Create a new click
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.ACTION_TYPE_DIALOG_CLICK_ACTION,
                ),
            ),
            // Select click location
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.CLICK_DIALOG_SELECT_POSITION_BUTTON,
                ),
            ),
            // Pick location
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.NextButton,
            ),
            // Save click
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.NextButton
            ),
            // Save event
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.NextButton
            ),
            // Save scenario
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.NextButton
            ),
            // Play scenario
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.NextOverlay,
                stepEndCondition = StepEndCondition.MonitoredViewClicked(
                    MonitoredViewType.FLOATING_MENU_BUTTON_PLAY,
                ),
            ),
            // Start game
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.Immediate,
                stepEndCondition = StepEndCondition.NextButton,
            ),
            // Game won
            TutorialStepData.TutorialOverlay(
                contentTextResId = R.string.message_tutorial_instructions_1,
                stepStartCondition = StepStartCondition.GameWon,
                stepEndCondition = StepEndCondition.NextButton,
            ),
        )
    )