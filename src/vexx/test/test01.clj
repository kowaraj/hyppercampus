(ns vexx.test.test01
  (:require
   [seesaw.core :as ss]))

(comment
  
(def f  (ss/frame
   :title "Vexx"
   :size [400 :by 400]))

(def t  (ss/text :id "asdf"
                 :text "aaaaaaaaaaaaa"
                 :editable? true
                 :multi-line? true
                 :wrap-lines? true
                 :columns 10
                 :rows 10))

(.add f t)
(-> f
    (ss/pack!)
    (ss/show!))

(.setBackground t (java.awt.Color. 240 240 240))
(.setBackground t java.awt.Color/LIGHT_GRAY)
(.setBackground t java.awt.Color/WHITE)

 [f (javax.swing.JFileChooser.)]

(let [ch (javax.swing.JFileChooser.)]
  (if (= javax.swing.JFileChooser/APPROVE_OPTION
         (.showOpenDialog ch f))
    (println "name = " (.getName (.getSelectedFile ch)))))
chooser.setCurrentDirectory(new java.io.File("."));


  (let [f-chooser (javax.swing.JFileChooser.)]
    (.setCurrentDirectory f-chooser (java.io.File. "./data"))
    
    (if (= javax.swing.JFileChooser/APPROVE_OPTION
           (.showOpenDialog f-chooser f))
      (let [f-name (.getPath (.getSelectedFile f-chooser))]
        (println f-name))))

(.dispose f)



)
