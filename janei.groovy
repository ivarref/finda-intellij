import com.intellij.openapi.ui.Messages
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.ui.Messages

var actionManager = ActionManager.getInstance()

// printf './janei.groovy\n' | entr -cc ./install.sh

IDE.application.invokeLater {
//    Thread.sleep(5000);
//    IDE.print("janei janei")
    AnAction action = actionManager.getAction("plugin.InstallFromDiskAction")
    var result = actionManager.tryToExecute(action, null, null, null, true)
    if (result.rejected) {
        Messages.showErrorDialog(result.error, "IDE action error")
    } else {
        Messages.showInfoMessage("Mjau", "Success")
    }
}

//Messages.showInfoMessage("Hello World2", "title2")
// /Applications/IntelliJ\ IDEA.app/Contents/MacOS/idea ideScript /Users/ire/.finda/integrations/finda_intellij/janei.groovy