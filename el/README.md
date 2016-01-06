# Overview

To run tests from the command line use:

```
emacs -batch -l ert -l euler.el -f ert-run-tests-batch-and-exit
```

For convenience, use the make command from the root `practice` directory.

# Working from the REPL

Open the `euler.el` buffer, and then run `M-x ert RET t RET` to run
unit tests

TODO: it'd be cool to run as emacs batch 
