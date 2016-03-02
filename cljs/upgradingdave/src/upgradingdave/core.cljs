(ns upgradingdave.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true]
   [upgradingdave.bmr-dev        :as bmr]
   [upgradingdave.compiler       :as compile]
   [upgradingdave.exif-dev       :as exif]
   [upgradingdave.pwd-dev        :as pwd]
   [upgradingdave.lattice-dev    :as lat]
   [upgradingdave.pcf-dev        :as pcf])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)


