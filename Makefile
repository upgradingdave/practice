elisp: el/euler.el
	emacs -batch -l ert -l el/euler.el -f ert-run-tests-batch-and-exit

js: FORCE
	cd js; mocha;

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

exif: 
	cd cljs/upgradingdave; echo "compiling exif.js ... "; \
		lein cljsbuild once prod-exif
	cd cljs/upgradingdave; echo "compiling exif-dev.js ... "; \
		lein cljsbuild once prod-exif-devcards

ics: ics-compile
	cp ${CLJS_HOME}/resources/public/js/compiled/ics.js \
		${BLOG_HOME}/resources/templates/js
	cp ${CLJS_HOME}/resources/public/js/compiled/ics-dev.js \
		${BLOG_HOME}/resources/templates/js

ics-compile: 
	rm -f cljs/upgradingdave/resources/public/js/compiled/ics.js
	cd cljs/upgradingdave; echo "compiling ics.js ... "; \
		lein cljsbuild once prod-ics
	rm -f cljs/upgradingdave/resources/public/js/compiled/ics-dev.js
	cd cljs/upgradingdave; echo "compiling ics-dev.js ... "; \
		lein cljsbuild once prod-ics-devcards

lattice: lattice-compile
	cp ${CLJS_HOME}/resources/public/js/compiled/lattice.js \
		${BLOG_HOME}/resources/templates/js
	cp ${CLJS_HOME}/resources/public/js/compiled/lattice-dev.js \
		${BLOG_HOME}/resources/templates/js

lattice-compile: 
	cd cljs/upgradingdave; echo "compiling lattice.js ... "; \
		lein cljsbuild once prod-lattice
	cd cljs/upgradingdave; echo "compiling lattice-dev.js ... "; \
		lein cljsbuild once prod-lattice-devcards

pcf: 
	cd cljs/upgradingdave; echo "compiling pcf.js ... "; \
		lein cljsbuild once prod-pcf
	cd cljs/upgradingdave; echo "compiling pcf-dev.js ... "; \
		lein cljsbuild once prod-pcf-devcards

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

resize: resize-compile
	cp ${CLJS_HOME}/resources/public/js/compiled/resize.js \
		${BLOG_HOME}/resources/templates/js
	cp ${CLJS_HOME}/resources/public/js/compiled/resize-dev.js \
		${BLOG_HOME}/resources/templates/js

resize-compile: 
	cd cljs/upgradingdave; echo "compiling resize.js ... "; \
		lein cljsbuild once prod-resize
	cd cljs/upgradingdave; echo "compiling resize-dev.js ... "; \
		lein cljsbuild once prod-resize-devcards

clean: 
	cd cljs/upgradingdave; echo "cleaning cljs ...";\
		lein clean
FORCE:
