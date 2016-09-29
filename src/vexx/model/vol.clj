(ns vexx.model.vol ;;instantaneous data (selection, current path)
  (:require
   [vexx.model.debug :as dbg]
   )
  )

(comment "----------------------------------------------------- listbox data -- ")

(def m-listbox-data (ref []))
(defn listbox-data [] m-listbox-data)
(defn listbox-data-set [lb-data]
  (dosync (ref-set m-listbox-data lb-data)))
;;(listbox-data-set [{:name "a" :index 1} {:name "b" :index 2}])
(defn listbox-data-get-item-name [item] (name (:name item)))

(defn listbox-data-get-items [lb-data] (:items lb-data))

(defn listbox-data-get-first [] (name (:name (first (:items @listbox-data)))))


(defn listbox-data-make
  "
  Tranform the list of kids names to the list of dicts of :name and :index
    from : [:2-1 :2-2]
    to   : ({:name :2-1, :index 0} {:name :2-2, :index 1})
  "
  [node] ;current node  
  (dbg/p node)
  (let [nodes (:nodes node)
        kids-by-name (keys nodes)
        list-of-dicts (map
                       (fn [kn n ki i]
                         (reduce into  [{} (hash-map kn n) (hash-map ki i)]))
                       (repeat :name) kids-by-name
                       (repeat :index) (range))
        items (apply vector list-of-dicts)]
    {:path (:path node)
     :selection (list-selection-get-name)
     :items items}))
     

;; (listbox-data-make {:1 "a" :2 "b" :3 "c" :4 "d"})
;; (reduce into '({} {:1 "a"} {:2 "b"}))
;; [:1 :2 :3 :4 :5] (range)
;; (map #(reduce into '({} (hash-map %1 %2) (hash-map %3 %4)) )
;;(reduce into '({} {:1 "a"} {:2 "b"})) ;;=> {:1 "a", :2 "b"}

(comment "----------------------------------------------------- kids data -- ")

(def m-kids-data (ref []))
(defn kids-data [] m-kids-data)
(defn kids-data-set [v-of-dicts]
  (dosync (ref-set m-kids-data v-of-dicts)))
;;(kids-data-set [{:name "a" :index 1} {:name "b" :index 2}])
(defn kids-item-get-name [item] (name (:name item)))

(defn kids-data-make
  "
  Tranform the list of kids names to the list of dicts of :name and :index
    from : [:2-1 :2-2]
    to   : ({:name :2-1, :index 0} {:name :2-2, :index 1})
  "
  [node] ;current node (parent)
  (dbg/p node)
  (let [kids-by-name (keys node)
        list-of-dicts (map
                       (fn [kn n ki i]
                         (reduce into  [{} (hash-map kn n) (hash-map ki i)]))
                       (repeat :name) kids-by-name
                       (repeat :index) (range))]
    (apply vector list-of-dicts)))



(comment "----------------------------------------------------- listbox sel -- ")


(def m-list-selection (ref {:index 0 :name nil}))

(defn list-selection [] m-list-selection)

(defn list-selection-get-name ;as a string
  []
  (:name @m-list-selection))
;(list-selection-get-name)
(defn list-selection-get-name-str
  []
  (assert false) ;;obsolete
  (name list-selection-get-name))
;(list-selection-get-name-str)
(defn list-selection-get-name-kw
  []
  (keyword (list-selection-get-name)))

;(list-selection-get-name)

(defn list-selection-set-by-name
  [sel-item-name]
  (dbg/p ": sel= " sel-item-name)
  (dosync (alter m-list-selection assoc :name sel-item-name)))
;; (list-selection-set-by-name "1")
;; (list-selection-set-by-name nil)
;; @m-list-selection

(defn list-selection-set-index
  [sel-item-index]
  (dosync (alter m-list-selection assoc :index sel-item-index)))
;; (list-selection-set-index 666)
;; @m-list-selection


(comment "----------------------------------------------------- content data -- ")

(def m-content-data (ref {:name "test-name" :content "test-content"}))
(defn content-data [] m-content-data)
(defn content-data-get-tabs
  "Get a list of tabs. Each tab is a dict {:name ... :content ... :type ...}
  "
  [item]
  nil)

(defn- content-data-make
  [node] ;current db node (selected)
  (:data node))

(defn content-data-set
  [db-node]
  (let [content (content-data-make db-node)]
    (dosync (ref-set m-content-data content))))
;;(content-data-set {:name "test-name" :content "test-content2"})






(comment "----------------------------------------------------- tags data -- ")

(def m-tags-data (ref ""))
(defn tags-data [] m-tags-data)
(defn tags-data-set [node]
  (dbg/p node)
  (dosync (ref-set m-tags-data (:tags node))))


