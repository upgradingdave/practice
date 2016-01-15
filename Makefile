elisp: el/euler.el
	emacs -batch -l ert -l el/euler.el -f ert-run-tests-batch-and-exit

# Haskell (wip, need to figure out how to write tests)
hs: cabal-sandbox
	cabal-install

cabal-sandbox: hs/.cabal-sandbox
	cabal sandbox init

clj: FORCE
	cd clj; echo "starting clj environment ... "; \
		boot dev

# cljs Builds
bmr: export BLOG_HOME=/Users/dparoulek/code/upgradingdave
bmr: export CLJS_HOME=/Users/dparoulek/code/practice/cljs/upgradingdave
bmr: compile-bmr
	cp ${CLJS_HOME}/resources/public/js/compiled/bmr.js \
		${BLOG_HOME}/resources/templates/js
	cp ${CLJS_HOME}/resources/public/js/compiled/bmr-dev.js \
		${BLOG_HOME}/resources/templates/js

compile-bmr:
	cd cljs/upgradingdave; echo "compiling bmr ... "; \
		lein cljsbuild once prod-bmr 
	cd cljs/upgradingdave; echo "compiling bmr-devcards ... "; \
		lein cljsbuild once prod-bmr-devcards

FORCE:
