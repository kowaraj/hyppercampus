(ns vexx.model.debug)

(defmacro p [& s]
  " Print with a function name prefix "
  `(println (-> (Throwable.) .getStackTrace first .getClassName) ~@s))

;; (defn test-print-fn []
;;   (p "arg1" "arg2"))
;; (test-print-fn)
