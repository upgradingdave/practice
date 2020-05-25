(ns upgradingdave.practice.words)

(def letters 
  {\A  1
   \B  2
   \C  3
   \D  4
   \E  5
   \F  6
   \G  7
   \H  8
   \I  9
   \J  10
   \K  11
   \L  12
   \M  13
   \N  14
   \O  15
   \P  16
   \Q  17
   \R  18
   \S  19
   \T  20
   \U  21
   \V  22
   \W  23
   \X  24
   \Y  25
   \Z  26})

(defn read-file [file-path]
  (slurp file-path))

(defn lines [file-path] 
  (clojure.string/split (read-file file-path) #"\n"))

(defn words [file-path]
  (reduce 
   (fn [a b] 
     (concat a (clojure.string/split b #"\s+")))
   []
   (lines file-path)))

(defn word-cost 
  "Calculate the cost of a word"
  [w]
  (reduce (fn [a b] (+ a (or (letters b) 0))) 0 
          (clojure.string/upper-case w)))

(defn find-all [cost]
  (let [all-words (atom [])]
    (with-open [rdr (clojure.java.io/reader "resources/10000words.txt")]
      (doseq [line (line-seq rdr)]
        (let [c (word-cost line)]
          (if (> c cost) 
            (swap! all-words conj line)))))
    @all-words
    ))

(defn word-costs 
  "Create a map of words and associated costs"
  [word-list]
  (map (fn [w] [w (word-cost w)])  word-list))

(defn one-hundred-dollar-words [w-costs]
  (filter (fn [[a b]] (= b 100)) w-costs))
