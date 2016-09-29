(ns vexx.test.test-db-add-node
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]   
   [vexx.model.item :as item]
))

(def db (ref (into {} (item/make-new-item "root"))))
;@db

(def path-1 [:root])

;; no nodes yet
(= (db/get-db-node db path-1) {})
;; add a node 
(= (db/add-db-node db path-1 "1")
   {:root {:nodes {:1 {:nodes {}, :path nil, :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}})
;; one node now
(= (db/get-db-node db path-1)
   {:1 {:nodes {}, :path nil, :link-to {}, :link-from {}}})

(def path-2 [:root :1])

;; add another node 
(= (db/add-db-node db path-2 "1-1")
   {:root {:nodes {:1 {:nodes {:1-1 {:nodes {}, :path nil, :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}})
;; check it
(= (db/get-db-node db path-2)
   {:1-1 {:nodes {}, :path nil, :link-to {}, :link-from {}}})

(def path-3 [:root :1 :1-1])

;; add attributes
(= (db/set-db-node-attr db path-3 :path "root-1-11")
   {:root {:nodes {:1 {:nodes {:1-1 {:nodes {}, :path "root-1-11", :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}})

(def path-4 [:root])

;; add another node 
(= (db/add-db-node db path-4 "2")
   {:root {:nodes {:1 {:nodes {:1-1 {:nodes {:2 {:nodes {}, :path nil, :link-to {}, :link-from {}}}, :path "root-1-11", :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}}, :path nil, :link-to {}, :link-from {}}})

(def path-5 [:root :2])

;; ;; check it
;; (db/get-db-node db path-4)

;; (db/del-db-node db path-4 "1")
;; ;@db

;; (db/get-db-node db path-4)
;; (db/add-db-node db path-4 "331")


