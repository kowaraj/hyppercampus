(ns vexx.controller.utils
  (:require
   [seesaw.core :as ss]

))

(defn choose-file-open
  [a-component]
  (let [f-chooser (javax.swing.JFileChooser.)]
    (.setCurrentDirectory f-chooser (java.io.File. "./data"))
    (if (= javax.swing.JFileChooser/APPROVE_OPTION
           (.showOpenDialog f-chooser a-component))
      (let [f-path (.getPath (.getSelectedFile f-chooser))]
        f-path))))

(defn choose-file-save
  [a-component]
  (let [f-chooser (javax.swing.JFileChooser.)]
    (.setCurrentDirectory f-chooser (java.io.File. "./data"))
    (if (= javax.swing.JFileChooser/APPROVE_OPTION
           (.showSaveDialog f-chooser a-component))
      (let [f-path (.getPath (.getSelectedFile f-chooser))]
        f-path))))
