## finda-intellij

Build the plugin:

```
./build.sh
```

Execute action `Install Plugin from Disk ...`.

Select `./build/distributions/pluggy-1.0-SNAPSHOT.zip`. Restart the IDE.

## Implemented

* Next: change, error.

* Window/tab: close.

## Goals

* Next: Across files.

* Window: split if needed, move otherwise.

## Non goals

* Clever install method from command line.

## Finda

Open tabs will then be written to `~/.finda/external_data/idea_project_<project_name>.json`

## Running an ideScript

```fish
/Applications/IntelliJ\ IDEA.app/Contents/MacOS/idea ideScript $HOME/.finda/integrations/finda_intellij/janei.groovy
```