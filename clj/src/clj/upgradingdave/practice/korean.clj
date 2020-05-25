(ns upgradingdave.practice.korean)

;; When studying Korean, I had notes with mixture of Korean and
;; English and I wanted to filter out any English. 

(def example (str "I will write an autobiography(자서전) later\n"
                  "(저는) 나중에 자서전을 쓸 거에요"))

;; Here's a transducer to filter out english characters

(defn filter-out-english 
  "filter out english characters in a string"
  []
  (filter (fn [c] 
            (let [i (int c)] 
              (not (or (and (>= i 65) (<= i 90)) 
                       (and (>= i 97) (<= i 122))))))))

(defn filter-out-english-lines
  "filter out english lines"
  []
  (filter (fn [l] 
            (if (empty? l) false
                (let [i (int (first l))] 
                  (not (or (and (>= i 65) (<= i 90)) 
                           (and (>= i 97) (<= i 122)))))))))

;; Here's a transducer to help deal with extra spaces and newlines.
;; Notice the mapcat ensures that the output will always be the same
;; shape as the input

(defn trim-chars [c n]
  "Ensure exactly n characters c in a row. For example, squash
  multiple spaces into single space or expand newlines into 2
  newlines"
  (comp (partition-by #{c})
        (mapcat #(if (= c (first %)) (repeat n c) %))))


;; put it all together, we filter out english characters, replace
;; multiple spaces with single space, and ensure each line is double
;; spaced (two line breaks between each line)
(def xf (comp (filter-out-english) 
              (trim-chars \space 1)
              (trim-chars \newline 2)))

(apply str (transduce xf conj example))
;; => " (자서전) \n\n(저는) 나중에 자서전을 쓸 거에요"

(defn transduce-file [fpath]
  (apply str (transduce xf conj (slurp fpath))))

(defn remove-english-iyagi
  "Remote english from in file containing iyagi and write results to out"
  [in out]
  (with-open [rdr (clojure.java.io/reader in)] 
    (spit out 
          (apply str (interpose "\n\n" (transduce (filter-out-english-lines) 
                                                  conj (line-seq rdr)))))))
