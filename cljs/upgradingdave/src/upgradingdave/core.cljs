(ns upgradingdave.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true]
   [upgradingdave.bmr-dev        :as bmr]
   [upgradingdave.generators-dev :as gen]
   [upgradingdave.pcf-dev        :as pcf]
   [upgradingdave.compiler       :as compile])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)


