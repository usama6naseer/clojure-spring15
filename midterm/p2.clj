(ns p2)
(defn sum [a-seq]
  (reduce + a-seq))

(defn subset-sum-helper [a-set current-set target]
  (if (= (sum current-set) target)
    [current-set]
    (let [remaining (clojure.set/difference a-set current-set)]
      (for [elem remaining
            solution (subset-sum-helper a-set
                                        (conj current-set elem)
                                        target)]
        solution))))

(defn subset-sum [a-set target]
  (subset-sum-helper a-set #{} target))

(println "kklk" (subset-sum #{1 3 4 10 9 23} 20))