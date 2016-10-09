(ns vexx.controller.debug-log
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface :as if]
   [vexx.controller.key-handler :as kh]

   [vexx.model.data :as data]
   [vexx.model.vol :as vol]
   [vexx.model.widgets :as widgets]
   [vexx.model.debug :as dbg]

   )
  )

(defn- watcher-fn
  [w d]
  ;(dbg/p d)
  (ss/config! w :text (str d)))
  
(defn add-a-watch
  [the-widget]
  ;(dbg/p)
  (add-watch (vol/debug-data)
             nil
             (fn [_ _ _ d]
               (watcher-fn the-widget d))))
