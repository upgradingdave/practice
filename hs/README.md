# Quick Start

To start a repl	

	cd codewars
	ghci
	
# Resources

https://wiki.haskell.org/Meta-tutorial

# Using Stack

As of 2020, I started using `Stack` (which builds on top of cabal). 

https://docs.haskellstack.org/

Stack uses `stack.yaml`

Use `stack --help`

# Using Cabal

`Common Architecture for Building Applications and Libraries` aka Cabal.

```
cabal sandbox init
cabal install -j
.cabal-sandbox/bin/hs-practice
```

Each Cabal package has a `.cabal` file which defines 1 or more
components, such as a library, executable, test suite and benchmarks.


# REPL

```
$ ghci
> :l hs-practice.hs 
```

