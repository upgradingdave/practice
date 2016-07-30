elisp: el/euler.el
	emacs -batch -l ert -l el/euler.el -f ert-run-tests-batch-and-exit

js: FORCE
	cd js; mocha;

# Haskell (wip, need to figure out how to write tests)
hs: cabal-sandbox
	cabal-install

cabal-sandbox: hs/.cabal-sandbox
	cabal sandbox init

# Clojure tasks

# Build and install password library
clj-build: FORCE
	cd clj; echo "compiling/installing common libs ..."; \
		boot build

clj: FORCE
	cd clj; echo "starting clj environment ... "; \
		boot dev

clean: 
	cd cljs/upgradingdave; echo "cleaning project ...";\
		lein clean
FORCE:
