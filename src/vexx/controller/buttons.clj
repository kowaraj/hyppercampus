(ns vexx.controller.buttons
  (:require
   [seesaw.core :as ss]
   [vexx.model.debug :as dbg]
   [vexx.controller.json :as c-json]
   [vexx.controller.utils :as c-utils]

   [vexx.model.widgets :as widgets]
   )
  )

(defn- b-load-action
  [e w]
  (dbg/p)
  (let [f-path (c-utils/choose-file-open w)]
    (c-json/load-from-file f-path)))

(defn- b-save-action
  [e w]
  (dbg/p)
  (let [f-path (c-utils/choose-file-save w)]
    (c-json/save-to-file f-path)))

(defn add-behavior
  [w]
  (dbg/p)
  (let [b-save (widgets/get-w :buttonSave)
        b-load (widgets/get-w :buttonLoad)]
    (ss/listen b-save :action #(b-save-action % w)) 
    (ss/listen b-load :action #(b-load-action % w)) ))

