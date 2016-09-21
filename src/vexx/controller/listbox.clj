(ns vexx.controller.listbox
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface :as if]

   [vexx.model.data :as data]
   [vexx.model.vol :as vol]
   [vexx.model.widgets :as widgets]

   )
  )

(defn add-watch-listbox-data
  "
  To add a watch on vol/listbox-data (a ref)
  to update the main listbox view (input arg)
  "
  [listbox-widget]

  (let [make-list-model
        (fn [items]
          (if (not (empty? items))
            (let [model (javax.swing.DefaultListModel.)]
              (doseq [item items]
                (.addElement model (vol/listbox-item-get-name item)))
              model)
            (let [model (javax.swing.DefaultListModel.)]
              model)))]

    (add-watch (vol/listbox-data)
               nil
               (fn [_ _ _ items]
                 (.setModel listbox-widget (make-list-model items))))))

;; (defn add-watch-listbox
;;   [lb]
;;   (m-l/add-watch-listbox lb))

;; (defn- make-list-model
;;   [items]
;;   (if (not (empty? items))
;;     (let [model (javax.swing.DefaultListModel.)]
;;       (doseq [item items] (.addElement model item))
;;       model)
;;     (let [model (javax.swing.DefaultListModel.)]
;;       model)))
;; ;(empty? false)


;; (defn add-watch-listbox
;;   [lb]
;;   (add-watch list-data nil
;;              (fn [_ _ _ items]
;;                (println " : model.model_list/watch-listbox--fn (listbox-watcher): items =" items)
;;                (.setModel lb (make-list-model items)))))

;; ;; (defn add-watch-listbox
;; ;;   [lb]
;; ;;   (m-l/add-watch-listbox lb))

;; (defn- delete-selected-item
;;   []
;;   (println " : model.model-list/delete-selected-item")
;;   (let [name (m-sel/get-xlist-sel)]
;;     (println " : model.model-list/delete-selected-item: name=" name)
;;     (m-db/del-item name) ;;change db
;;     ))

;; (defn delete-item
;;   []
;;   (delete-selected-item)) ;; update the model


;; (defn add-tab-to-item 
;;   [& {:keys [tab-name tab-type tab-content]
;;       :or {tab-name "default tab name"
;;            tab-type :text
;;            tab-content "default tab content"}
;;       }
;;    ]
;;   (let [item (m-u/make-data-item :name tab-name
;;                                  :type tab-type
;;                                  :content tab-content)]
;;     (m-di/add-data-item-to-sel-item item))) ;; update the model


;; (defn get-data-for-selection
;;   [sel]
;;   (println " : controller.controller_listbox/get-data-for-selection:  sel= " sel)
;;   (m-log/set-log-data (str "xlist selected value: " sel))

;;   (if sel
;;     (m-sel/set-xlist-sel sel)
;;     (println " : controller.controller_listbox/get-data-for-selection:  nothing selected"))

;;   (let [sel (m-sel/get-xlist-sel)
;;         sel-data (m-sel/get-data-of-sel-item sel)]
;;     (println " : controller.controller_listbox/get-data-for-selection:  sel-data = " sel-data)
;;     sel-data))


;; (defn get-data 
;;   "
;;   Returns data for xlist's model.
;;   Called from view.xlist/make-xlist
;;   "
;;   []
;;   (m-db/get-items-by-name))

;; (defn get-list-str
;;   []
;;   ;;(clojure.string/join "-\n" @m-s/list-data))
;;   (clojure.string/join ",, " (get-data)))


;; ;;(println "TODO: FIX: remove direct access to MODEL from VIEW !! ")

;; (defn listener-focusgained
;;   [e] 
;;   ;;(println " : view.listbox/listener-focusgained, sel=" (ss/selection e))
;;   (let [
;;         jl (.getSource e)
;;         sel-index (.getSelectedIndex jl)
;;         ]
;;     ;;(println " : view.listbox/listener-focusgained: sel-index=" sel-index)
;;     ))

;; (defn listener-focuslost
;;   [e] 
;;   ;;;;(println " : view.listbox/listener-focuslost: sel=" (ss/selection e))
;;   (let [
;;         jl (.getSource e)
;;         sel-index (.getSelectedIndex jl)
;;         ]
;;     ;;(println " : view.listbox/listener-focuslost: sel-index=" sel-index)
;;     ))


    
(defn listener-selection
  [e]
  (vol/list-selection-set-by-name (ss/selection e))

;  (let [sel-data (data/sel-item-data) ]
    ;(c-tpane/add-tabs sel-data)
;    )
)

;; (defn listener-selection
;;   [e tpane tf-tags]

;;   (let [sel (keyword (ss/selection e))]
;;     ;(println " : view.listbox/listener-selection: sel=" sel)
;;     (c-tags/update-view tf-tags sel)  ; update tags text-field of selected item

;;     (if sel
;;       (let [sel-data (c-l/get-data-for-selection sel)
;;             sel-index (.getSelectedIndex (.getSource e))
;;             ]
;;         (m-sel/set-xlist-sel-index sel-index) ;store the selected index
;;         ;(println " : view.listbox/listener-selection: (sel) sel-data=" sel-data ", sel-index=" sel-index)
;;         (v-tp/add-tabs tpane sel-data))
;;       (let [sel-old (m-sel/get-xlist-sel)
;;             ;_ (println "sel-old = " sel-old)
;;             sel-data (c-l/get-data-for-selection sel-old)
;;             sel-index (m-sel/get-xlist-sel-index) ; retrieve stored index
;;             ]
;;         ;(println " : view.listbox/listener-selection: (old) sel-data=" sel-data ", sel-index=" sel-index)
;;         (.setSelectedIndex (.getSource e) sel-index)
;;         (v-tp/add-tabs tpane sel-data)))
;;     ))

;; ;(defn- x-tf-shown  []  (println "!!! x-tf-shown !!!")  )

;; (defn listener-keyreleased
;;   [e tpane] 
;;   (if (= java.awt.event.KeyEvent/VK_DELETE (.getKeyCode e)) ;; handle DEL pressed
;;     (let [jl (.getSource e)
;;           sel-index (.getSelectedIndex jl)
;;           new-size (dec (.getSize (.getModel jl)))
;;           new-sel-index (if (>= sel-index new-size) (dec new-size) sel-index)]
;;       ;;(println " : view.listbox/listener-keyreleased: si=" sel-index ", ns=" new-size "ni=" new-sel-index)
;;       (c-l/delete-item)
;;       (.setSelectedIndex jl new-sel-index)
;;       ))

;;   (if (= \newline (.getKeyChar e)) ;; handle ENTER pressed
;;     (let [jl (.getSource e)
;;           sel-index (.getSelectedIndex jl)
;;           ]
;;       (let [t (ss/text :text "Enter new Tab name") ; :component x-tf-shown)
;;             result (javax.swing.JOptionPane/showInputDialog
;;                     (ss/border-panel :size [100 :by 100])
;;                     t
;;                     "Input"
;;                     javax.swing.JOptionPane/QUESTION_MESSAGE
;;                     nil
;;                     (to-array [:text :pic :other])
;;                     "Titan"
;;                     )
;;             ]
;;         (let [a (ss/text t)
;;               b result]
;;           (if result
;;             (let []
;;               (c-l/add-tab-to-item :tab-name a :tab-type b)
;;               (.fireSelectionValueChanged jl sel-index sel-index false)
;;               ))
;;           ))
;;       (.setSelectedIndex jl sel-index)))
;;   )
;; ;(def x (create-view))


(defn add-behavior
  []
  (ss/listen (widgets/get-w :xlist)
             :selection listener-selection
             ;; :selection #(listener-selection % tpane tf-tags)
             ;; :key-released #(listener-keyreleased % tpane)
             ;; :focus-lost #(listener-focuslost %)
             ;; :focus-gained #(listener-focusgained %)
             ))
