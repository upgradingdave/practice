(ns purelyfun.379)

;; My solution to the weekly challenge here: 
;; https://purelyfunctional.tv/issues/purelyfunctional-tv-newsletter-379-get-produces-unexpected-behavior-as-expected/

(comment

  ;; My approach is to use a very basic LR parser.

  ;; First, I create a nested map datastructure to act as a lookup
  ;; table. For example: 
  (index-dict ["hello" "hey"])
  {:h {:e {:y {:end true}, :l {:l {:o {:end true}}}}}}

  ;; Next, I parse one character at a time. Each time I parse a
  ;; character, I can check the lookup table to test whether a
  ;; sequence of characters exists in the dictionary. For example, if
  ;; I've parsed "h" and "e", I can do this:
  (get-in (index-dict ["hello" "hey"]) [:h :e])

  ;; I use the :end keyword in my lookup table to know when found a
  ;; word. For example this returns `nil`:
  (get-in (index-dict ["hello" "hey"]) [:h :e :end])

  ;; ... and this returns `true`:
  (get-in (index-dict ["hello" "hey"]) [:h :e :y :end])

  ;; The `parse` method continues to look at each character until it
  ;;finds a word or can't find a match in the lookup table for the
  ;;characters it's seen so far.

  ;; If parse finds word, then there are two possibilities: 1. That's
  ;; the only match possible, or 2. It might be possible to find a
  ;; longer match.

  ;; So whenever a word is found, `parse` will branch to try these two
  ;; possibilities.

  ;; Of course, this branching creates a tree (nested vector)
  ;; result. So the final step is to traverse the tree and remove the
  ;; nested lists.
  )

(defn deep-merge
  "Deep merge maps"
  [& maps]
  (if (every? map? maps) (apply merge-with deep-merge maps) (last maps)))

(defn index-word
  "Create a recursive map structure representing characters in a word"
  [word]
  (reduce (fn [r c] (hash-map (keyword (str c)) r)) {:end true} (reverse word)))

(defn index-dict
  [dict]
  (apply deep-merge (map index-word dict)))

(defn parse
  "Parse `text` one character at a time, building a tree of possible
  lists of words"
  [lkup c text path result]
  (let [k (keyword (str c))
        new-path (conj path k)
        matched (get-in lkup new-path)]
    (cond

      ;; no match for next character
      (nil? matched)
      (if (get-in lkup (conj path :end))
        ;; found a word! Add it and continue parsing
        (parse lkup c text [] (conj result (apply str (map name path))))
        ;; no more matches can be found, so return results
        result)

      ;; found a match for the next character
      :else
      (if (get-in lkup (conj path :end))
        ;; found a word at this ending, but might also find longer
        ;; word if we keep parsing
        [(parse lkup c text [] (conj result (apply str (map name path))))
         (parse lkup (first text) (rest text) (conj path k) result)]
        
        ;; no word here, keep going
        (parse lkup (first text) (rest text) (conj path k) result))

      )))

(defn segmentations
  "Create a tree of possible matches. The possible match lists will be
  the leaves of the tree. Filter the tree to return the leaves."
  [text dictionary]
  (let [lkup (index-dict dictionary)
        results (parse lkup (first text) (rest text) [] [])
        result-tree (tree-seq (partial every? vector?) seq results)]
    (map #(apply str (interpose " " %))
         (filter (complement (partial every? vector?))
                 result-tree))))

(comment
  ;; test against 1000 words
  (def words
    (with-open [rdr (clojure.java.io/reader "./resources/10000words.txt")]
      (let [words (line-seq rdr)]
        (segmentations "hellothere" words))))

  ;; penisland ... I only read this as "pen" "island" at first, but I
  ;; get it now ;-)

  (segmentations "penisland" ["pen" "is" "land" "island" "penis"])
  ;;=> ("pen is land" "pen island" "penis land")
  
  )
