(ns vexx.test.test-db-get-node
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]   
   [vexx.model.item :as item]
))

;; (def db (ref {
;;               :root {
;;                      :nodes
;;                      (reduce into
;;                              [{}
;;                               (item/make-new-item "1")
;;                               (item/make-new-item "2")
;;                               ])
;;                      :info "no info available"
;;                      }
;;               }))

;; ;@db

            
;; (def path-1 [:root :1])
;; (def path-2 [:root :2])

;; (db/get-db-node db [:root])
;; (db/get-db-node db [:root :1])
;; (:nodes (:root @db))
;; (db/add-db-node db path-1 "1-1")
