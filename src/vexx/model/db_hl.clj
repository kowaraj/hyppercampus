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

(defn get-root-node
  "called when main listbox selection changes"
  []
  (let [db (data/db)
        node-path @(path/current-path)]
    (db/get-db-node db node-path)))
;(keys (db/get-db-node (data/db) [:root]))

(defn get-node
  "called when main listbox selection changes (only need selected node)"
  []
  (let [db (data/db)
        sel-node-name (vol/list-selection-get-name)
        node-path (path-hl/get-node-path sel-node-name)]
    (db/get-db-node db node-path)))

(defn get-node-nodes
  "called when main listbox selection changes (only need selected node)
  Deprecated: use (:nodes (get-node))
  "
  []
  (let [db (data/db)
        sel-node-name (vol/list-selection-get-name)
        node-path (path-hl/get-node-path sel-node-name)]
    (db/get-db-node-nodes db node-path)))

(defn add-node
  [node-name]
  (let [db (data/db)
        path (path-hl/get-selected-node-path)
        ]
    ;; (if (get-db-node-fn db name) ; check if exists
    ;; @db
    (db/add-db-node db path node-name)))

(defn add-node-to-root
  [node-name]
  (let [db (data/db)
        path @(path/current-path)
        ]
    (db/add-db-node db path node-name)))

(defn del-selected-node
  []
  (let [db (data/db)
        sel-node-name (vol/list-selection-get-name)
;;;        node-path (path-hl/get-node-path sel-node-name)]
        path @(path/current-path)]
    (db/del-db-node db path sel-node-name)))
;(path-hl/get-node-path "1")
;(db/del-db-node (data/db) [:root] "1")
  

