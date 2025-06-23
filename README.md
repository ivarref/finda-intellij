## finda-intellij

Build the plugin:

```
gradle buildPlugin
```

Execute action `Install Plugin from Disk ...`.

Select `./build/distributions/pluggy-1.0-SNAPSHOT.zip`. Restart the IDE.

Open tabs will then be written to `$HOME/.finda/external_data/idea_project_<project_name>.json` 