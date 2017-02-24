(use 'clojure.string)

(def vec-link [{:link "h1"} {:out-links ["h2" "h3" "h4"]}
           {:link "h2"} {:out-links ["h1" "h3" "h4"]}
           {:link "h3"} {:out-links ["h2" "h1" "h4"]}
           {:link "h4"} {:out-links ["h2"]}
           ])

;----------------PART A------------------
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
(defn readbyline [vc]
  (for [x vc]
    {:link (trim x) :out-links (into [] (map trim (get-vec (subvec vc (+ 1 (.indexOf vc x))) x)))}
     )
  )
(defn read-file [file]
  (into [] (filter (fn[x] (not (= (:out-links x) [])))
          (readbyline (clojure.string/split-lines (slurp file)))))
)

;(def vec-link (read-file "C:/Users/Usama/clojure/fake.txt"))
(println vec-link)
(println "---------------------------------------------------------")
;------------------------------------------------------------
;----------------PART B------------------

(defn check-inlinks [in]
  (conj {} {:link (:link in)
            :out-links (:out-links in)
            :in-links (map :link (filter (fn [x] (contains? (set (:out-links x)) (:link in) )) vec-link))})
  )
(defn find-inlinks [v]
  (into [] (map check-inlinks v))
  )

;(def new-vec (find-inlinks vec-link))
;(println new-vec)
(def new-vec (find-inlinks vec-link))
(println new-vec)
(println "---------------------------------------------------------")
;-----------------------------------------------------------
;----------------PART C------------------

(defn sort-vector [v]
  (into [] (sort-by (fn [x] (count(:in-links x))) v))
  )
(defn sort-inlinks [v]
  (println (sort-vector v))
  )
;(sort-inlinks new-vec)
(sort-inlinks new-vec)
(println "---------------------------------------------------------")
;-----------------------------------------------------------
;----------------PART D------------------
(defn remove-loop [v]
  (conj {} {:link (:link v)
            :out-links (into [](filter (fn [x] (not (contains? (set (:in-links v)) x))) (:out-links v)))
            :in-links (into [](filter (fn [x] (not (contains? (set (:out-links v)) x))) (:in-links v)))}) 
  )
;(defn sort-vector [v]
;  (into [] (sort-by (fn [x] (count(:in-links x))) v))
;  )
(defn sort-loop [v]
  (sort-vector (into [] (map remove-loop v)))
  )
;(defn check-inlinks [in]
;  (conj {} {:link (:link in)
;            :out-links (:out-links in)
;            :in-links (into [](map :link (filter (fn [x] (contains? (set (:out-links x)) (:link in) )) vec-link)))})
;  )
(defn sort-inlinks-loop [v]
;  (def new-vec (into [] (map check-inlinks v)))
;  (println (sort-vector new-vec))
  (println (sort-loop v))
  )
(sort-inlinks-loop new-vec)
(println "---------------------------------------------------------")
;-----------------------------------------------------------
;----------------PART F------------------
(defn reached [] true)
(defn r-able? [in out n]
  (cond (not (nil? in)) (do
  (def temp1 (filter (fn [x] (= (:link x) in)) vec-link))
  (cond
    (> n 0)
     (do 
     (def temp (into {}  temp1))
     (if (contains? (set (:out-links temp)) out)
       [true]
     (do 
;       (filter (fn[z] (or (= z true)(= z [true])))
        (filter (fn[y] (not(nil? y)))(filter (fn [x1] (not(= x1 ())))
                (map (fn [x] (r-able? x out (- n 1))) (:out-links temp))))))
     )
    )))
  )
(defn reachable? [x y]
;  (println (r-able? x y 100))
  (def ty (distinct (r-able? x y 500)))
  (println ty)
    (cond (or (contains? (set ty) [true]) (= ty [true]))
    (do true)
    :else (do false))
  )

;(reachable? "hello1" "hello4")
;-----------------------------------------------------------