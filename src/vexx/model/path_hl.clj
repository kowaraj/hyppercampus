(ns vexx.model.path-hl
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.path :as path]
   [vexx.model.vol :as vol] ;; might need be removed if circular reference
   )
  )

(defn get-node-path
  [node-name]
;;;  {:pre [(= (type node-name) java.lang.String)]}
  (if node-name
    (path/conj-node @(path/current-path) node-name)
    @(path/current-path)))

(defn get-selected-node-path
  []
  (get-node-path (vol/list-selection-get-name)))
;(get-selected-node-path)

(defn go-level-down-selnode
  []
  ;(dbg/p)
  (let [k (vol/list-selection-get-name-kw)]
    ;(dbg/p k)
    (path/go-level-down k)))

(defn go-level-up-to-parent
  []
  ;(dbg/p)
  (path/go-level-up))




