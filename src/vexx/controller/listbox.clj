(ns vexx.controller.listbox
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface-2 :as if-2]
   [vexx.controller.key-handler :as kh]

   [vexx.model.debug :as dbg]
   [vexx.model.data :as data]
   [vexx.model.vol :as vol]
   [vexx.model.widgets :as widgets]

   )
  )

(defn- update-model
  [w d-new]
  (dbg/p d-new)
  (let [items (vol/listbox-data-get-items d-new)
        model (javax.swing.DefaultListModel.)]
    (if (not (empty? items))
      (let []
        (doseq [item items]
          (.addElement model (vol/listbox-data-get-item-name item)))
        model)
      (let []
        model))))

(defn- update-listbox-selection
  [w d-old d-new]
  (if (vol/is-stack-action-level-up)
    (dbg/p "yes")
    (dbg/p "no")))
    ;;(.setSelectedIndex w 1)))

(defn- update-listbox
  " Update listbox model and selection
  "
  [w d-old d-new]
  (dbg/p d-new)

  (cond
    (= :path-changed (:cause d-new) )
    (.setModel w (update-model w d-new))
        
    (= :db-changed (:cause d-new))
    (if (not (= (count (vol/listbox-data-get-items d-old))
                (count (vol/listbox-data-get-items d-new))))
      (.setModel w (update-model w d-new)))
        
    :else
    (dbg/p "unknown cause, don't update the listbox model"))
  (dbg/p d-new)

  (update-listbox-selection w d-old d-new)
  )


(defn add-watch-listbox-data
  "
  To add a watch on vol/listbox-data (a ref)
  to update the main listbox view (input arg)
  "
  [listbox-widget]
  (add-watch (vol/listbox-data)
             nil
             (fn [_ _ lb-data-old lb-data-new]
               (dbg/p lb-data-new)
               (update-listbox listbox-widget
                               lb-data-old
                               lb-data-new))))

(defn listener-selection
  [e]
  (let [sel-node-name (ss/selection e)
        lb (.getSource e) ]
    (dbg/p "sel = " sel-node-name)
    (if sel-node-name
      ;; just update the selection
      (vol/list-selection-set-by-name sel-node-name)
      ;; Nothing selected, deduce the index from history
      (let [sel-index (vol/list-selection-get-index)]
        (dbg/p "sel-index = " sel-index)
        (.setSelectedIndex lb sel-index)))))


(defn- get-user-input
  []
  (let [t  (ss/text :text "")
        ret (javax.swing.JOptionPane/showInputDialog
             (ss/border-panel :size [100 :by 100])
             t
             "Input"
             javax.swing.JOptionPane/QUESTION_MESSAGE
             nil
             (to-array [:text :pic :other])
             "Titan") ]
    
    [(ss/text t) ret])) ;;(get-user-input)
        


(defn listener-keyreleased
  [e]
  
  (if (kh/is-right e)
    (if-2/jump-to-kids-of-sel-node))

  (if (kh/is-left e)
    (if-2/jump-to-parent-of-sel-node))

  (if (kh/is-ctrl-r e)
    (if-2/rename-sel-node (if-2/get-new-name e)))

  (if (kh/is-del e)
    (if-2/delete-selected-node))

  (if (kh/is-enter e)
    (let [[t ret] (get-user-input)]
      (if ret
        (if-2/add-kid-to-selected-node {:name t :type ret}))))
  )



(defn add-behavior
  []
  (ss/listen (widgets/get-w :xlist)
             :selection listener-selection
             :key-released listener-keyreleased
             ))
;(add-behavior)

;   (ss/listen (ss/listbox) :list-selection #( println %))
