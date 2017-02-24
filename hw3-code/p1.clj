;(defn readbyline [vc]
;  (for [x vc]
;    (println x)
;     (cond
;       (= (get x 0) space)
;       (do )
;       :else (do ))
;    )
;)
(defn count-space[n x]
  (cond (= (first x) \space)
        (do (count-space (+ n 1) (rest x)))
        :else (do (+ n 0)))
  )


(defn get-same-level [vc n]
;  (cond 
;    (= (count-space 0 (first vc)) n)
;    (do (println (first vc))
;        (get-same-level (rest vc) n))
;    :else (do ["mm"] (readbyline vc)))
    
  (for [x vc :while (not (= (count-space 0 x) n))]
    x
    )
  )

(defn get-level [x vc]
  (if (= (count-space 0 x) (count-space 0 (first vc))) 
    (do (readbyline vc))
    (do (into [] (get-same-level vc (count-space 0 (first vc))))))
  )

(defn readbyline [vc]
  (if (not (nil? (first vc)))
    (do (println (conj{} {:link (first vc) :out-link (into [] (get-level (first vc) (rest vc)))}))))
;          (cond
;            (= (get (first vc) 0) \space)
;            (do (println "space found"))
;            :else (do (println "no space found")
;                      {:link (first vc)}
;                      ))
;         )
;        (readbyline (rest vc))))
  )
 


(defn read-file [file]
  (readbyline (clojure.string/split-lines (slurp file)))
;  (into [] (get-same-level (clojure.string/split-lines (slurp file)) 2))
)

(read-file "C:/Users/Usama/clojure/fake.txt")
;(count-space 0 "  abc")

;(def digits (seq [1 2 3]))
;(for [x1 digits x2 digits] (* x1 x2))

;(for [x (range 3 7)] 
;  (* x x))





