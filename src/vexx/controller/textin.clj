(ns vexx.controller.textin
  (:require
   [seesaw.core :as ss]

;;;   [vexx.controller.interface :as interface]

   [vexx.model.debug :as dbg]
   [vexx.model.db-hl :as db-hl]
   [vexx.model.widgets :as widgets]
   )
  )

(defn- keytyped-enter
  [e]
  (let [w-text-in (.getSource e)
        new-el-name (ss/text w-text-in)]
    (db-hl/add-node-to-root new-el-name)))
  
(defn- listener-keytyped
  [e]
  (let [ch (.getKeyChar e)]
    (dbg/p ch)
    (cond
      (= ch \newline) (keytyped-enter e)
      :else nil)))

(defn add-behavior
  []
  (dbg/p)
  (let [w (widgets/get-w :text-in)]
    (ss/listen w
               :key-typed listener-keytyped
               )))
;; (add-behavior)
