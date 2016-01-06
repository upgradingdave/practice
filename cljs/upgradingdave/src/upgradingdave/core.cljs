(ns upgradingdave.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true]
   [upgradingdave.generators :as gen]
   [upgradingdave.compiler   :as compile])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)


