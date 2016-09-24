(ns vexx.test.test-interface
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]   
   [vexx.model.db-hl :as db-hl]   
   [vexx.model.item :as item]
   [vexx.model.path :as path]
   [vexx.model.path-hl :as path-hl]
))

;; should trigger the db watcher
(dosync (ref-set m-db {:root {:nodes {} } :info "no info available"}))

;; should trigger the db watcher
(db/add-db-node (data/db) [:root] "1")



;(db/get-db-node-kids (data/db) @(path/current-path) (vol/list-selection-get-name))
;(vol/kids-data-make {:2-1 {:nodes {}, :path nil, :link-to {}, :link-from {}}, :2-2 {:nodes {}, :path nil, :link-to {}, :link-from {}}})
;(vol/list-selection-get-name)
;(update-kids-data)

;;(map hash-map (range) [1 2 3])
;;(println (vol/listbox-data-make node))))
;;(db/get-db-node (data/db) @(path/current-path))
;;(keys (db/get-db-node (data/db) @(path/current-path)))
;;(update-listbox-data)
;;(:nodes (:root @(data/db)))
;(db/add-db-node (data/db) [:root] "1")
;(db/add-db-node (data/db) [:root] "2")
;(db/add-db-node (data/db) [:root :2] "2-1")
;(db/add-db-node (data/db) [:root :2] "2-2")
;(db/add-db-node (data/db) [:root :1] "1-1")
;(db/add-db-node (data/db) [:root :1] "1-2")
;(db/add-db-node (data/db) [:root :1] "1-3")
;;(update-listbox-data)
;;(update-listbox-data)
;;@(data/db)
;;@(path/current-path)
;;(path/go-level-down :2)
;;(update-listbox-data)
;;(path/go-level-up)
;; @(vol/listbox-data)

;;(db/add-db-node (data/db) [:root :1] "1-3")
;;(db/get-db-node (data/db) @(path/current-path))
;;(db/get-db-node (data/db) [:root :1])
;;(db/get-db-node (data/db) [:root :1 :1-1])
;;(db/add-db-node (data/db) [:root :1] "1-666")
;;(db/get-db-node (data/db) [:root :1 :1-666])
;;(db/add-db-node (data/db) [:root] "root-666")
;;(db/add-db-node (data/db) [:root] "root-667")
