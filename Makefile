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

cljs: FORCE
	cd cljs/upgradingdave; echo "starting figwheel ... "; \
		lein figwheel

# cljs Builds
BLOG_HOME=/Users/dparoulek/code/upgradingdave
CLJS_HOME=/Users/dparoulek/code/practice/cljs/upgradingdave
bmr: bmr-compile
	cp ${CLJS_HOME}/resources/public/js/compiled/bmr.js \
		${BLOG_HOME}/resources/templates/js
	cp ${CLJS_HOME}/resources/public/js/compiled/bmr-dev.js \
		${BLOG_HOME}/resources/templates/js

bmr-compile:
	cd cljs/upgradingdave; echo "compiling bmr.js ... "; \
		lein cljsbuild once prod-bmr 
	cd cljs/upgradingdave; echo "compiling bmr-dev.js ... "; \
		lein cljsbuild once prod-bmr-devcards

pwd: pwd-compile
	cp ${CLJS_HOME}/resources/public/js/compiled/pwd.js \
		${BLOG_HOME}/resources/templates/js
	cp ${CLJS_HOME}/resources/public/js/compiled/pwd-dev.js \
		${BLOG_HOME}/resources/templates/js

pwd-compile:
	cd cljs/upgradingdave; echo "compiling pwd.js ... "; \
		lein cljsbuild once prod-pwd
	cd cljs/upgradingdave; echo "compiling pwd-dev.js ... "; \
		lein cljsbuild once prod-pwd-devcards

pcf: 
	cd cljs/upgradingdave; echo "compiling pcf.js ... "; \
		lein cljsbuild once prod-pcf
	cd cljs/upgradingdave; echo "compiling pcf-dev.js ... "; \
		lein cljsbuild once prod-pcf-devcards

FORCE:
