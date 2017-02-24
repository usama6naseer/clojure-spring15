(use 'clojure.string)
(def test [{:link h1} {:out-links [h2 h3 h4]}
           {:link h2} {:out-links [h1 h3 h4]}
           {:link h3} {:out-links [h2 h1 h4]}
           {:link h4} {:out-links [h2]}
           ])



(defn count-space[n x]
  (cond (= (first x) \space)
        (do (count-space (+ n 1) (rest x)))
        :else (do (+ n 0)))
  )
(defn get-vec[vc x]
  (filter (fn[x2] 
            (= (count-space 0 x2) (+ 2 (count-space 0 x))))
           (for [x1 vc :while (> (count-space 0 x1) (count-space 0 x))]
     x1)
  )
)

(defn check-true [t] (cond (contains? (set t) true)
                       (do true)
                       :else (do false)))

(defn readbyline [vc]
  (for [x vc]
    {:link (trim x) :out-links (into [] (map trim (get-vec (subvec vc (+ 1 (.indexOf vc x))) x)))}
     )
  )
(defn read-file [file]
  (into [] (filter (fn[x] (not (= (:out-links x) [])))
          (readbyline (clojure.string/split-lines (slurp file)))))
)

(def vec-link (read-file "C:/Users/Usama/clojure/fake.txt"))
(println vec-link)

;(defn readbyline [vc]
;  (for [x vc]
;    (if (not (check-true(map (fn[c] (println (trim x) (subvec vc 0 (.indexOf vc x)))(= (trim x) (trim c)))(subvec vc 0 (.indexOf vc x)))))
;    {:link (trim x) :out-links (into [] (map trim (get-vec (subvec vc (+ 1 (.indexOf vc x))) x)))}
;     ))
;  )
;(defn read-file [file]
;  
;  
;  (def temp1 (into [](readbyline (clojure.string/split-lines (slurp file)))))
;  (println temp1)
;;  (filter (fn[x]
;;            (:link)) temp1)
;  
;;  (into [] (filter (fn[x] (not (= (:out-links x) [])))
;;          (readbyline (clojure.string/split-lines (slurp file)))))
;)
;;(check-true [false true false])
;(read-file "C:/Users/Usama/clojure/fake.txt")
;;------------------------------------------------------------
;
