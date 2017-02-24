(def vec-link [
               {:link "hello1" :out-links ["world11" "world12" "hello2" "hello3"]}
               {:link "hello2" :out-links ["world21" "hello1" "hello3"]}
               {:link "hello3" :out-links ["world31" "world32" "hello4"]}
               {:link "hello4" :out-links ["world41" "hello1" "hello2" "hello3"]}
               ])
(defn remove-loop [v]
  (conj {} {:link (:link v)
            :out-links (into [](filter (fn [x] (not (contains? (set (:in-links v)) x))) (:out-links v)))
            :in-links (into [](filter (fn [x] (not (contains? (set (:out-links v)) x))) (:in-links v)))}) 
  )
(defn sort-loop [v]
  (def pruned-vec (into [] (map remove-loop v)))
;  (println pruned-vec)
  (sort-vector pruned-vec)
  )
  
(defn sort-vector [v]
  (into [] (sort-by (fn [x] (count(:in-links x))) v))
  )
(defn check-inlinks [in]
  (conj {} {:link (:link in)
            :out-links (:out-links in)
            :in-links (into [](map :link (filter (fn [x] (contains? (set (:out-links x)) (:link in) )) vec-link)))})
  )
(defn find-inlinks [v]
  (def new-vec (into [] (map check-inlinks v)))
  (println (sort-vector new-vec))
  (println (sort-loop new-vec))
  
;  (def sorted-vec (into [] (sort-by (fn [x] (count(:in-links x))) new-vec)))
;  (println (get sorted-vec 0))
;  (println new-vec)
  )
;(println (:out-links (get vec-link 0)))

(find-inlinks vec-link)
;-----------------------------------------------------------