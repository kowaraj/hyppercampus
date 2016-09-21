;; For the second listbox.
;; To display kids of the selected item in the first listbox

(ns vexx.controller.kids
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface :as if]

   [vexx.model.data :as data]
   [vexx.model.vol :as vol]
   [vexx.model.widgets :as widgets]

   )
  )

(defn add-watch-kids-data
  "
  To add a watch on vol/kids-data (a ref)
  to update the second listbox view (input arg)
  "
  [listbox-widget]

  (let [make-list-model
        (fn [items]
          (if (not (empty? items))
            (let [model (javax.swing.DefaultListModel.)]
              (doseq [item items]
                (.addElement model (vol/kids-item-get-name item)))
              model)
            (let [model (javax.swing.DefaultListModel.)]
              model)))]

    (add-watch (vol/kids-data)
               nil
               (fn [_ _ _ items]
                 (.setModel listbox-widget (make-list-model items))))))

(defn listener-selection
  [e]
;  (data/list-selection-set-name (ss/selection e))

;  (let [sel-data (data/sel-item-data) ]
    ;(c-tpane/add-tabs sel-data)
;    )
)

(defn add-behavior
  []
  (ss/listen (widgets/get-w :kids)
             :selection listener-selection
             ;; :selection #(listener-selection % tpane tf-tags)
             ;; :key-released #(listener-keyreleased % tpane)
             ;; :focus-lost #(listener-focuslost %)
             ;; :focus-gained #(listener-focusgained %)
             ))
