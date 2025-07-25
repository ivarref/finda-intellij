package com.github.ivarref.ideafinda;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class PreviousWhateverAction extends AnAction {

    @Override
    public final void update(@NotNull AnActionEvent event) {
        final Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(null != project);
    }

    @Override
    public final void actionPerformed(@NotNull AnActionEvent e) {
        if (NextWhateverAction.NextAction.ERROR == NextWhateverAction.currentAction) {
            NextWhateverAction.executeActionId("GotoPreviousError", e);
        } else if (NextWhateverAction.NextAction.CHANGE == NextWhateverAction.currentAction) {
            NextWhateverAction.executeActionId("JumpToLastChange", e);
        } else {
            Messages.showInfoMessage("::" + NextWhateverAction.currentAction,
                    "Previous action is ...");
        }
    }

    public final @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

}
