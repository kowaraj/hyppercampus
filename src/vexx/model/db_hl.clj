;; High-level interface for db

(ns vexx.model.db-hl
  (:require
   [vexx.model.data :as data]
   [vexx.model.item :as item]
   [vexx.model.path :as path]
   [vexx.model.path-hl :as path-hl]
   [vexx.model.debug :as dbg]
   [vexx.model.vol :as vol]
   [vexx.model.db :as db]
   ))

(defn get-node
  "called when main listbox selection changes (only need selected node)"
  []
  (let [db (data/db)
        sel-node-name (vol/list-selection-get-name-str)
        node-path (path-hl/get-node-path sel-node-name)]
    (db/get-db-node db node-path)))

(defn get-node-nodes
  "called when main listbox selection changes (only need selected node)"
  []
  (let [db (data/db)
        sel-node-name (vol/list-selection-get-name-str)
        node-path (path-hl/get-node-path sel-node-name)]
    (db/get-db-node-nodes db node-path)))

  

