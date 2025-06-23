import sys
import pathlib
import os
import subprocess
import time

def debug(v):
    path = os.environ['HOME'] + '/.finda/integrations/finda_intellij/ideaopen.log'
    with open(path, 'a') as fd:
        fd.write(str(v) + '\n')

def get_project_path(folder_name):
    assert isinstance(folder_name, pathlib.Path)
    candidate = os.path.join(folder_name, '.idea')
    if pathlib.Path(candidate).is_dir():
        debug('Found ' + candidate)
        return str(folder_name)
    else:
        if str(folder_name) == '/':
            debug('Giving up')
            return None
        else:
            debug('Not found ' + candidate)
            return get_project_path(folder_name.parent.absolute())

def run_main():
    last_arg = sys.argv[-1]
    debug('sys.argv is: ' + str(sys.argv))
    if last_arg.endswith('/Contents/Resources/script'):
        debug('Ignoring standalone execution')
    else:
        debug('Processing: ' + last_arg)
        path = pathlib.Path(last_arg)
        if path.is_file():
            project_path = get_project_path(path.parent.absolute())
            if project_path is not None:
                debug('project path is: ' + project_path)
                # subprocess.run(['open', '-n', "/Applications/IntelliJ IDEA.app", '--args',
                #                 project_path])
                # time.sleep(0.1)
                subprocess.run(["/Applications/IntelliJ IDEA.app/Contents/MacOS/idea",
                                project_path, path])
            else:
                subprocess.run(["/Applications/IntelliJ IDEA.app", '--args', path])
        else:
            debug('Could not process: ' + last_arg)

def run_main_outer():
    try:
        debug(('*' * 40) + ' Starting ' + ('*' * 40))
        run_main()
        debug(('*' * 40) + ' Exiting ' + ('*' * 40))
    except Exception as e:
        debug('Exception occurred:')
        debug(str(e))

if __name__ == "__main__":
    run_main_outer()