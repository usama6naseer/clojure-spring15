(def vec-link [
               {:link "hello1" :out-links ["world11" "world12" "hello2"]}
               {:link "hello2" :out-links ["world21" "hello1" "hello3"]}
               {:link "hello3" :out-links ["world31" "world32" "hello4"]}
               {:link "hello4" :out-links ["world41" "hello1" "hello2" "hello3"]}
               ])
(defn check-inlinks [in]
  (conj {} {:link (:link in)
            :out-links (:out-links in)
            :in-links (map :link (filter (fn [x] (contains? (set (:out-links x)) (:link in) )) vec-link))})
  )
(defn find-inlinks [v]
  (def new-vec (into [] (map check-inlinks v)))
  (println new-vec)
  )
;(println (:out-links (get vec-link 0)))

(find-inlinks vec-link)
;-----------------------------------------------------------
