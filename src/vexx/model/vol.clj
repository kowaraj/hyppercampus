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

(declare list-selection-get-name)

(defn listbox-data-make
  "
  Tranform the list of kids names to the list of dicts of :name and :index
    from : [:2-1 :2-2]
    to   : ({:name :2-1, :index 0} {:name :2-2, :index 1})
  Arguments:
    node      : current node
    the-cause : the reason why the update required
  "
  [node the-cause] ;current node
  {:pre [(or (= the-cause :db-changed)
             (= the-cause :path-changed))]}
  ;(dbg/p node)
  
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
     :items items
     :cause the-cause}))
     
(defn listbox-data-get-index
  [a-name]
  {:pre [(= java.lang.String (type a-name))]}
  (let [items (:items @(listbox-data))]
    (let [items-f (filter #(= (keyword a-name) (:name %)) items)]
      (if items-f
        (:index (first items-f))
        0))))
;(listbox-data-get-index :123)

      

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
  ;;;(dbg/p node)
  (let [kids-by-name (keys node)
        list-of-dicts (map
                       (fn [kn n ki i]
                         (reduce into  [{} (hash-map kn n) (hash-map ki i)]))
                       (repeat :name) kids-by-name
                       (repeat :index) (range))]
    (apply vector list-of-dicts)))



(comment "----------------------------------------------------- actions stack -- ")

(def m-last-action (ref {:sel-name :root
                         :action :launched}))
(def m-stack (ref [@m-last-action]))

;@m-last-action
;@m-stack

;; List of actions:
;;  :level-down   - save current sel node to stack
;;  :pop          - remove last action from stack
;;  :delete       - (node deleted) ...

(defn- stack-push
  [an-action]
  (let [m {:sel-name (list-selection-get-name)
           :action an-action}]
    (dosync
     (alter m-stack conj m)
     (ref-set m-last-action m))))

(defn stack-add-node
  [a-name]
  (dosync (ref-set m-last-action {:sel-name a-name :action :add-node})))


(defn stack-go-level-down
  []
  (stack-push :level-down))

(defn stack-go-level-up
  []
  (dbg/p)
  (let [m (stack-pop)]
    (dbg/p m)
    (dosync (ref-set m-last-action {:sel-name (:sel-name m) :action :level-up}))
    (println "up sel = " m)))
;(stack-go-level-up)

(defn stack-pop
  []
  (let [m (peek @m-stack)]
    (dosync (alter m-stack pop))
            ;(ref-set m-last-action :pop))
    m))

(defn is-stack-action-level-up
  []
  (if (= :level-up (:action @m-last-action))
    true
    false))
;(is-stack-action-level-up)
;(stack-push :level-up)


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
  ;;(dbg/p ": sel= " sel-item-name)
  (dosync (alter m-list-selection assoc :name sel-item-name)))
;; (list-selection-set-by-name "1")
;; (list-selection-set-by-name nil)
;; @m-list-selection

(defn list-selection-set-index
  [sel-item-index]
  (dosync (alter m-list-selection assoc :index sel-item-index)))
;; (list-selection-set-index 666)
;; @m-list-selection

(defn list-selection-get-index
  []
  (dbg/p)
  (if (is-stack-action-level-up)
    (let [sel-name (:sel-name @m-last-action)]
      (listbox-data-get-index sel-name))))
    ;;(.setSelectedIndex w 1)))


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
;;  (dbg/p node)
  (dosync (ref-set m-tags-data (:tags node))))


(comment "----------------------------------------------------- log/debug data -- ")

(def m-debug-data (ref ""))
(defn debug-data [] m-debug-data)
(defn debug-data-set [node]
  ;(dbg/p node)
  (let [nodes (keys (:nodes node))
        debug-node (assoc node :nodes nodes)
        debug-node (assoc debug-node :data "...") ]
    ;(dbg/p debug-node)
    (dosync (ref-set m-debug-data debug-node))))


