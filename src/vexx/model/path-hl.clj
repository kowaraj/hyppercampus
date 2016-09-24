(ns vexx.model.path-hl
  (:require
   [vexx.model.path :as path]
   [vexx.model.vol :as vol] ;; might need be removed if circular reference
   )
  )

(defn get-node-path
  [node-name]
  {:pre [(= (type node-name) java.lang.String)]}
  (path/conj-node @(path/current-path) node-name))

(defn get-selected-node-path
  []
  (get-node-path (vol/list-selection-get-name-str)))
;(get-selected-node-path)




