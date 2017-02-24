(ns p2)
(def vec [])
(def tree {:link :out-link})

(defn insert [tree url]
  (if (nil? tree)
    (do
    (cond
            (= (get url 0) \space)
            (do (println "space found")
                {:link (:link tree) :out-link (conj vec url))})
            :else (do (println "no space found")
                      {:link url}
                      ))
    ))
  )

(defn readbyline [vc]
;  (println (first vc))
  (if (not (nil? (first vc)))
    (do (println (first vc))
        (let [fchar (get (first vc) 0)]
        (println fchar)
          (cond
            (= fchar \space)
            (do (println "space found"))
            :else (do (println "no space found")
                      {:link (first vc)}
                      ))
         )
        (readbyline (rest vc)))
  )
  
)

(defn read-file [file]
  (readbyline (clojure.string/split-lines (slurp file)))
)

(read-file "C:/Users/Usama/clojure/fake.txt")

;(defn bst-insert [bst data]
;  (if (nil? bst)
;    {:data data}
;    (if (= data (:data bst))
;      bst
;      (if (< data (:data bst))
;        {:data (:data bst)
;         :left (bst-insert (:left bst) data)
;         :right (:right bst)}
;        {:data (:data bst)
;         :left (:left bst)
;         :right (bst-insert (:right bst) data)}))))

