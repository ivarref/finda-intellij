package com.github.ivarref.ideafinda;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import org.jetbrains.annotations.NotNull;

public class ChooseInitialNextAction extends AnAction {

    @Override
    public final void update(@NotNull AnActionEvent event) {
        final Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(null != project);
    }

    @Override
    public final void actionPerformed(@NotNull AnActionEvent e) {
        String id = "com.github.ivarref.ChooseInitialNextAction";
        ActionGroup action = (ActionGroup) e.getActionManager().getAction(id);
        ListPopup actionGroupPopup = JBPopupFactory.getInstance().createActionGroupPopup(
                "Go to next ...",
                action,
                e.getDataContext(),
                JBPopupFactory.ActionSelectionAid.MNEMONICS,
                false);
        actionGroupPopup.showCenteredInCurrentWindow(e.getProject());
    }

    public final @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }
}
