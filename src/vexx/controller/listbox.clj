(ns vexx.controller.listbox
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface :as if]

   [vexx.model.debug :as dbg]
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

    
(defn listener-selection
  [e]
  (dbg/p)
  (let [sel-node-name (ss/selection e)]
    (if sel-node-name
      (vol/list-selection-set-by-name sel-node-name)
      (let [first-node-name (name (:name (first @(vol/listbox-data))))]
        (if first-node-name
          (vol/list-selection-set-by-name first-node-name)
          ))
      )))

(defn listener-keyreleased
  [e] 
  ;;(dbg/p e)
  
  (if (= java.awt.event.KeyEvent/VK_RIGHT (.getKeyCode e))
    (let [jl (.getSource e)]
      (dbg/p "right")
      (if/jump-to-kids-of-sel-node)
      ))

  (if (= java.awt.event.KeyEvent/VK_LEFT (.getKeyCode e))
    (let [jl (.getSource e)]
      (dbg/p "left")
      (if/jump-to-parent-of-sel-node)
      ))

  (if (= java.awt.event.KeyEvent/VK_DELETE (.getKeyCode e)) ;; handle DEL pressed
    (let [
          jl (.getSource e)
          ;; sel-index (.getSelectedIndex jl)
          ;; new-size (dec (.getSize (.getModel jl)))
          ;; new-sel-index (if (>= sel-index new-size) (dec new-size) sel-index)
          ]
      (dbg/p "del")
      ;;(println " : view.listbox/listener-keyreleased: si=" sel-index ", ns=" new-size "ni=" new-sel-index)
      ;; (c-l/delete-item)
      ;; (.setSelectedIndex jl new-sel-index)
      (if/delete-selected-node)
      ))

  (if (= \newline (.getKeyChar e)) ;; handle ENTER pressed
    (let [jl (.getSource e)
          ;;sel-index (.getSelectedIndex jl)
          ]
      (let [t (ss/text :text "") 
            result (javax.swing.JOptionPane/showInputDialog
                    (ss/border-panel :size [100 :by 100])
                    t
                    "Input"
                    javax.swing.JOptionPane/QUESTION_MESSAGE
                    nil
                    (to-array [:text :pic :other])
                    "Titan"
                    )
            ]
        (let [a (ss/text t)
              b result]
          (if result
            (let []
              ;; (c-l/add-tab-to-item :tab-name a :tab-type b)
              ;; (.fireSelectionValueChanged jl sel-index sel-index false)
              (if/add-kid-to-selected-node {:name a :type b})
              ))
          )))
    );;;    (dbg/p "something else pressed"))
  )


(defn add-behavior
  []
  (ss/listen (widgets/get-w :xlist)
             :selection listener-selection
             :key-released listener-keyreleased
             ))
