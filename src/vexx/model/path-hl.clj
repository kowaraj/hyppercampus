(ns vexx.model.path-hl
  (:require
   [vexx.model.path :as path]
   )
  )

(defn get-node-path
  [node-name]
  {:pre [(= (type node-name) java.lang.String)]}
  (path/conj-node @(path/current-path) node-name))


