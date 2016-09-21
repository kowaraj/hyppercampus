(ns vexx.test.test-interface
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]   
   [vexx.model.item :as item]
))

;; should trigger the db watcher
(dosync (ref-set (data/db) {})) 

;; should trigger the db watcher
(db/add-db-node (data/db) [:root] "1")



