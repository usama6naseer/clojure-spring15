(def vec-link [
               {:link "hello1" :out-links ["world11" "world12" "hello2" "hello3"]}
               {:link "hello2" :out-links ["world21" "hello1" "hello3"]}
               {:link "hello3" :out-links ["world31" "world32" "hello4"]}
               {:link "hello4" :out-links ["world41" "hello1" "hello2" "hello3"]}
               ])
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

(defn remove-loop [v]
  (conj {} {:link (:link v)
            :out-links (into [](filter (fn [x] (not (contains? (set (:in-links v)) x))) (:out-links v)))
            :in-links (into [](filter (fn [x] (not (contains? (set (:out-links v)) x))) (:in-links v)))}) 
  )
(defn sort-vector [v]
  (into [] (sort-by (fn [x] (count(:in-links x))) v))
  )
(defn sort-loop [v]
  (sort-vector (into [] (map remove-loop v)))
  )
(defn check-inlinks [in]
  (conj {} {:link (:link in)
            :out-links (:out-links in)
            :in-links (into [](map :link (filter (fn [x] (contains? (set (:out-links x)) (:link in) )) vec-link)))})
  )
(defn find-inlinks [v]
  (def new-vec (into [] (map check-inlinks v)))
;  (println (sort-vector new-vec))
;  (println (sort-loop new-vec))
  (println (reachable? "hello1" "hello5"))
;  (println "kkk" (r-able? "hello1" "hello1" 100))
  
  )

(find-inlinks vec-link)

;  (println (:out-links temp))
;  (reachable? (get temp 2) out)
;  (map (fn [x] (reachable? x out)) (:out-links temp))
;    (cond
;    (contains? (set (:out-links temp)) out)
;    (do (println "IF")(true))
;    :else (do (println "ELSE") (map (fn [x] (reachable? x out)) temp))
;(defn cg [x] 
;  (if (x 1)
;    (true)
;    (false)))
;(cg 1)

;-----------------------------------------------------------