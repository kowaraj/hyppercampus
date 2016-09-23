;; Access to the db throw db-ll
;; 2 related ns: 
;;   1. db-ll - callees
;;   2. db-hl - callers

(ns vexx.model.db
  (:require
   [vexx.model.data :as data]
   [vexx.model.item :as item]
   [vexx.model.path :as path]
   [vexx.model.debug :as dbg]
   [vexx.model.db-ll :as db-ll]
   ))


(defn get-db-node
  [db path]
  (db-ll/get-db-node-fn db (path/nodes path)))


(defn add-db-node
  [db path node-name]
  (dbg/p node-name path)
  (db-ll/add-db-node-fn db (path/nodes path) node-name))

(defn set-db-node-attr
  [db path attr-name attr-val]
  (db-ll/set-db-node-attr-fn db (path/attr path) attr-name attr-val))

(defn del-db-node
  [db path node-name]
  (db-ll/del-db-node-fn db (path/nodes path) node-name))

(defn del-db-node-attr
  [db path attr-name]
  (db-ll/del-db-node-attr-fn db (path/nodes path) attr-name))


