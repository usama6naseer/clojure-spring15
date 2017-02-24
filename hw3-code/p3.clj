(defn readbyline [vc]
;  (println (first vc))
  (if (not (nil? (first vc)))
    (do (println (first vc))
      (readbyline (rest vc)))
  )
  
)

(readbyline [1 2 3 4 5 6 7 8])

    
    
;(defn readbyline [vc]
;  (println (first vc))
;  (if (not (nil? (first vc)))
;    (readbyline (rest vc))
;    (println "Vector empty")
;    )
;  
;)
;
;(readbyline [1 2 3 4 5 6 7 8])
;------------------------------------------------ 