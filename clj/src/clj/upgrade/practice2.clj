(ns upgrade.practice2)

;;https://app.codesignal.com/interview-practice/task/HmNvEkfFShPhREMn4
(defrecord ListNode [value next])

(defn vec->listNode [v]
  (let [l (atom nil)]
    (doseq [n (reverse v)] (reset! l (ListNode. n @l)))
    @l))

(defn isListPalindrome [^ListNode l]
  (let [;; first pass, count number of nodes in the list
        len  (loop [l l cnt 0]
               (if l (recur (:next l) (inc cnt)) cnt))
        halfway (int (/ len 2))
        ;; second pass, find first node in second half of list
        [half acc] (loop [l l cnt 0 acc nil]
                     (if (< cnt halfway)
                       (recur (:next l) (inc cnt) (cons (:value l) acc))
                       [l acc]))
        ;; if list length is odd, move half one to the right
        half (if (odd? len) (:next half) half)
        ]
    ;; now compare both halfs
    (loop [half half acc acc]
      (if half
        (if (= (:value half) (first acc))
          (recur (:next half) (rest acc))
          false)
        (empty? acc)))))

;; https://app.codesignal.com/interview-practice/task/RvDFbsNC3Xn7pnQfH
(defn addTwoHugeNumbers-add [^String a ^String b]
  (let [[n1 n2] (if (> (count a) (count b)) [a b] [b a])]
    (loop [idx (dec (count n1)) idx2 (dec (count n2)) r 0 acc ""]
      (let [i1 (Integer. (str (nth n1 idx 0)))
            i2 (Integer. (str (nth n2 idx2 0)))
            s1 (+ i1 i2 r)
            s2 (if (>= s1 10) (- s1 10) s1)
            r1 (if (>= s1 10) 1 0)]
        (if (< idx 0)
          (if (> r 0)
            (str r acc)
            acc)
          (recur (dec idx) (dec idx2) r1 (str s2 acc)))))))

(defn addTwoHugeNumbers [a b]
  (let [to-stack (fn [l] (loop [l l acc nil cnt 0] 
                           (if l (recur (:next l)
                                        (cons (:value l) acc)
                                        (inc cnt))
                               [acc cnt])))
        [stacka cnta] (to-stack a)
        [stackb cntb] (to-stack b)
        [s1 s2] (if (< cnta cntb) [stackb stacka] [stacka stackb])]
    (loop [s1 s1 s2 s2 r 0 acc []]
      (let [a (first s1)
            b (first s2)]
        (if (nil? a)
          (if (> r 0)
            (cons r acc)
            acc)
          (let [i1 (addTwoHugeNumbers-add (str (+ a r)) (str b))
                i2 (Integer. i1)
                i3 (if (>= i2 10000) (- i2 10000) i2)
                r1 (if (>= i2 10000) 1 0)]
            (recur (rest s1) (rest s2) r1 (cons i3 acc))))))))


;;https://app.codesignal.com/skill-test/ngQTG9kra7GE9pnnK
(defn newNumeralSystem [number]
  (letfn [(to-int [c] (- (int c) 65))
          (to-char [c] (char (+ c 65)))
          (to-str [[a b]] )]
    (let [d (to-int number)]
      (filter identity
              (for [i (range (inc d))
                    j (range i (inc d))]
                (if (= d (+ i j))
                  (str (to-char i) " + " (to-char j))))))))

;;https://app.codesignal.com/interview-practice/task/6rE3maCQwrZS3Mm2H
(defn mergeTwoLinkedLists [l1 l2]
  (loop [l1 l1 l2 l2 acc []]
    (cond

      (and l1 l2)
      (if (< (:value l1) (:value l2))
        (recur (:next l1) l2 (conj acc (:value l1)))
        (recur l1 (:next l2) (conj acc (:value l2))))

      l1
      (recur (:next l1) l2 (conj acc (:value l1)))

      l2
      (recur l1 (:next l2) (conj acc (:value l2)))

      :else
      acc)))

;;https://app.codesignal.com/interview-practice/task/XP2Wn9pwZW6hvqH67
(defn reverseNodesInKGroups [l k]
  (loop [l l cnt 0 acc '() result []]
    (if l
      (if (< cnt k)
        (recur (:next l) (inc cnt) (conj acc (:value l)) result)
        (recur (:next l) 1 (conj '() (:value l)) (apply conj result acc)))
      (if (< cnt k)
        (apply conj result (reverse acc))
        (apply conj result acc)))))


;;https://app.codesignal.com/challenge/uf5m9HooxrJQoYZMn
(defn selectionProcess-file [file]
  (let [subject (second (re-matches #"(\w+).txt" (.getName file)))
        lines (line-seq (clojure.java.io/reader file))]
    [subject
     (map (fn [line]
            (let [[_ name score]
                  (re-matches #"([a-zA-Z ]+)(\d+)" line)
                  score (Integer. score)
                  name (clojure.string/trimr name)]
              [name score]))
          lines)]))

(defn selectionProcess-dir [dir]
  (println "dir: " dir)
  (let [files (filter #(.isFile %) (file-seq dir))]
    (reduce (fn [m v]
              (let [[subject results] (selectionProcess-file v)]
                (assoc-in m [subject] results))) {} files)))

(defn selectionProcess [root-dir]
  (let [dirs (filter #(.isDirectory %)
                     (file-seq (clojure.java.io/file root-dir)))
        results (reduce
                 (fn [m v]
                   (let [r (selectionProcess-dir v)]
                     (merge-with into m r)))
                 {} dirs)]
    (doseq [[k v] (sort results)]
      (println (str k ":"))
      (doseq [s (sort (fn [[_ s1] [_ s2]] (compare s2 s1))
                      (take 4 (into #{} v)))]
        (println (first s)))
      (println ""))
    1))

;;https://app.codesignal.com/interview-practice/task/xrFgR63cw7Nch4vXo
(defn groupingDishes [dishes]
  (let [by-ingredient
        (reduce (fn [m v]
                  (merge-with
                   into m
                   (let [dish (first v)
                         ingrds (rest v)]
                     (into (sorted-map)
                           (map (fn [n] [n (sorted-set dish)]) ingrds)))))
                (sorted-map)
                dishes)]
    (reduce (fn [m [ingrd dishes]]
              (if (>= (count dishes) 2)
                (conj m (concat [ingrd] dishes))
                m)) [] by-ingredient)))

;;https://app.codesignal.com/interview-practice/task/3PcnSKuRkqzp8F6BN
(defn areFollowingPatterns [strings patterns]
  (loop [strings strings patterns patterns accp {} accs {}]
    (let [s (first strings)
          p (first patterns)
          p' (get accp s)
          s' (get accs p)]
      (if (nil? s)
        true

        (cond

          (and (nil? p') (nil? s'))
          (recur (rest strings) (rest patterns)
                 (assoc accp s p) (assoc accs p s))

          (nil? s')
          (if (= p' p)
            (recur (rest strings) (rest patterns)
                   (assoc accp s p) (assoc accs p s))
            false)
          
          (nil? p')
          (if (= s' s)
            (recur (rest strings) (rest patterns)
                   (assoc accp s p) (assoc accs p s))
            false)

          :else
          (if (and (= s' s) (= p' p))
            (recur (rest strings) (rest patterns)
                   (assoc accp s p) (assoc accs p s))
            false))))))

;;https://app.codesignal.com/interview-practice/task/njfXsvjRthFKmMwLC
(defn containsCloseNums [nums k]
  (loop [idx 0 nums nums acc {}]
    (if (empty? nums)
      false

      (let [n (first nums)
            i (get acc n)
            res (reduce (fn [m v] (or m (<= (- idx v) k))) false i)]
        (if res
          true
          (recur (inc idx) (rest nums) (update-in acc [n] conj idx)))))))

;;https://app.codesignal.com/interview-practice/task/rMe9ypPJkXgk3MHhZ
(defn possibleSums-repeat [coin quantity results]
  (loop [idx 0 sums results acc #{}]
    (if (>= idx quantity) acc
        (let [new-sums (into #{} (for [sum sums] (+ coin sum)))
              new-acc (into acc new-sums)]
          (recur (inc idx) new-sums new-acc)))))

(defn possibleSums [coins quantity]
  (loop [coins coins quans quantity results #{}]
    (if (empty? coins)
      (count results)
      (let [coin (first coins)
            quan (first quans)
            results (into results (for [result results] (+ coin result)))
            results (into results #{coin}) 
            results (if (<= (dec quan) 0)
                      results
                      (into
                       results
                       (possibleSums-repeat coin (dec quan) results)))]
        (recur (rest coins) (rest quans) results)))))

;;https://app.codesignal.com/interview-practice/task/ZTqKqNwK6ZL6GDpJ5
(defn hasDeadlock-ns [connections ns visited results]
  ;;(println "ns: " ns)
  (if (empty? ns)
    false
    (let [n (first ns)
          result (if (or (contains? visited n) (get results n))
                   true
                   (hasDeadlock-ns connections (get connections n)
                                   (conj visited n) results))]
      ;; (println "n: " n)
      ;; (println "visited: " visited)
      ;; (println "result: " result)
      (if result
        true
        (recur connections (rest ns) visited (assoc results n false))))))

(defn hasDeadlock [connections]
  (let [cnt (count connections)]
    (loop [v 0 results {}]
      ;; (println "=======")
      ;; (println "v: " v)
      ;; (println "results: " results)      
      (if (and (<= v cnt) (not (reduce (fn [m [_ v]] (or m v)) false results)))
        (let [result
              (hasDeadlock-ns connections (get connections v) #{v} results)]
          (if result
            true
            (recur (inc v) (assoc results v result))))
        false))))

;; https://app.codesignal.com/interview-practice/task/TG4tEMPnAc3PnzRCs
(defrecord Tree [value left right])

(defn hasPathWithGivenSum-node [^Tree t s acc]
  ;; (println "t: " (:value t))
  ;; (println "acc: " acc)
  (let [left (:left t)
        right (:right t)]
    (or 
     (if left
       (hasPathWithGivenSum-node left s (+ acc (:value left)))
       (and (nil? right) (= acc s)))
     (if right
       (hasPathWithGivenSum-node right s (+ acc (:value right)))
       (and (nil? left) (= acc s))))))

(defn hasPathWithGivenSum [^Tree t s]
  (hasPathWithGivenSum-node t s (:value t)))

;; Here's another way to do it
(defn hasPathWithGivenSum2 [^Tree t s]
  (let [{:keys [left right value]} t]
    (cond
      (and (nil? t) (= s value)) true

      (nil? t) false

      (and (nil? left) (nil? right) (= s value)) true

      :else
      (or (hasPathWithGivenSum2 left (- s value))
          (hasPathWithGivenSum2 right (- s value))))))

(comment
  (def t2 (Tree. 5
                 (Tree. 7 nil nil)
                 nil))
  (def t (Tree. 4
                (Tree. 1
                       (Tree. -2 nil (Tree. 3 nil nil))
                       nil)
                (Tree. 3
                       (Tree. 1 nil nil)
                       (Tree. 2 (Tree. -2 nil nil) (Tree. -3 nil nil))))))

;;https://app.codesignal.com/interview-practice/task/tXN6wQsTknDT6bNrf
(defn isTreeSymmetric [t]
  (loop [nodes [t]]
    (println "======= ")
    (println "nodes: " nodes)    
    (if (empty? nodes)
      true
      (let [vals (map :value nodes)
            cnt (count vals)
            head (take (/ cnt 2) vals)
            tail (reverse (take-last (/ cnt 2) vals))]
        (println head)
        (println tail)
        (if (and (> cnt 1) (not (= head tail))) 
          false
          (let [children
                (reduce
                 (fn [m {:keys [left right]}]
                   (-> m
                       (conj left)
                       (conj right)))
                 []
                 (filter #(not (nil? %)) nodes))]
            (recur children)))))))

(comment
  (def t3 (Tree. 1
                 (Tree. 2
                        nil
                        (Tree. 3 nil nil))
                 (Tree. 2
                        nil
                        (Tree. 3 nil nil))))

  (def t1 (Tree. 1
                 (Tree. 2
                        (Tree. 3 nil nil)
                        (Tree. 4 nil nil))
                 (Tree. 2
                        (Tree. 4 nil nil)
                        (Tree. 3 nil nil)))))

;;https://clojuredocs.org/clojure.core/tree-seq
(defn findProfessionParent [lvl pos]
  (println "=====")
  (println "lvl: " lvl)
  (println "pos: " pos)  
  (if (<= pos 0)
    [(reduce * (repeat lvl 2)) "Engineer"]

    (let [[start parent]
          (findProfessionParent (dec lvl) (bigint (/ (- pos 1) 2)))
          idx (- (bigint pos) start)
          end (+ start (bigint (reduce * (repeat lvl 2))))]
      (println "lvl: " lvl)
      (println "start: " start)
      (println "end: " end)
      (if (= parent "Engineer")
        [end (if (odd? idx) "Doctor" "Engineer")]
        [end (if (odd? idx) "Engineer" "Doctor")]))))

(defn findProfession [level pos]
  (let [level (dec level)
        idx (dec (+ pos
                    (reduce + (map #(reduce * (repeat (bigint %) (bigint 2)))
                                   (range level)))))]
    (println "level: " level)
    (println "idx: " idx)
    (second (findProfessionParent level idx))))

;;https://app.codesignal.com/interview-practice/task/jAKLMWLu8ynBhYsv6
(defn kthSmallestInBST-left [t ps]
  (loop [t t ps ps] (if (nil? (:left t))
                      [t ps]
                      (recur (:left t) (conj ps t)))))

(defn kthSmallestInBST-next [t ps]
  ;; go right, or up
  (if (:right t)
    (kthSmallestInBST-left (:right t) ps)
    [(first ps) (rest ps)]))

(defn kthSmallestInBST [t k]
  (let [[smallest ps] (kthSmallestInBST-left t '())]
    (loop [n smallest ps ps idx 0]
      (if (>= idx (dec k))
        (:value n)
        (let [[n1 ps1] (kthSmallestInBST-next n ps)]
          (recur n1 ps1 (inc idx)))))))

(comment
  (def t1 (Tree. 3
                 (Tree. 1 nil nil)
                 (Tree. 5
                        (Tree. 4 nil nil)
                        (Tree. 6 nil nil)))))

;;https://app.codesignal.com/interview-practice/task/mDpAJnDQkJqaYYsCg
(defn isSubtree-equal [t1 t2]
  (let [right (if (nil? (:right t1))
                (nil? (:right t2))
                (isSubtree-equal (:right t1) (:right t2)))
        left (if (nil? (:left t1))
               (nil? (:left t2))
               (isSubtree-equal (:left t1) (:left t2)))]
    (and (= (:value t1) (:value t2)) left right)))

(defn isSubtree [t1 t2]
  (if (nil? t1) (nil? t2)
      (if (isSubtree-equal t1 t2) true
          (or (isSubtree (:left t1) t2) (isSubtree (:right t1) t2)))))

(defn isSubtree2 [t1 t2]
  (let [tseq (partial tree-seq #(not (nil? %)) #(vector (:left %) (:right %)))]
    (if (nil? t1) (nil? t2)
        (if (= (tseq t1) (tseq t2)) true
            (or (isSubtree (:left t1) t2) (isSubtree (:right t1) t2))))))

(comment

  (def t1
    (map->Tree {
                :value 5,
                :left {
                         :value 10,
                         :left {
                                  :value 4,
                                  :left {
                                           :value 1,
                                           :left nil,
                                           :right nil
                                           },
                                  :right {
                                            :value 2,
                                            :left nil,
                                            :right nil
                                            }
                                  },
                         :right {
                                   :value 6,
                                   :left nil,
                                   :right {
                                             :value -1,
                                             :left nil,
                                             :right nil
                                             }
                                   }
                         },
                :right {
                          :value 7,
                          :left nil,
                          :right nil
                          }
                }))

  (def t2 (map->Tree {
                   :value 10,
                   :left {
                          :value 4,
                          :left {
                                 :value 1,
                                 :left nil,
                                 :right nil
                                 },
                          :right {
                                  :value 2,
                                  :left nil,
                                  :right nil
                                  }
                          },
                   :right {
                           :value 6,
                           :left nil,
                           :right {
                                   :value -1,
                                   :left nil,
                                   :right nil
                                   }
                           }
                      }))

  (def t3 (Tree. 1
                 (Tree. 2 nil nil)
                 (Tree. 2 nil nil)))

  (def t4 (Tree. 2
                 (Tree. 1 nil nil)
                 nil))

  )

;; https://app.codesignal.com/interview-practice/task/AaWaYxi8gjtbqgp2M
(defn restoreBinaryTree [inorder preorder]
  (if (empty? inorder)
    nil
    (let [v (first preorder)
          idx (first (keep-indexed #(when (= %2 v) %1) inorder))
          left (restoreBinaryTree (take idx inorder) (take idx (rest preorder)))
          right (restoreBinaryTree (drop (inc idx) inorder)
                                   (drop (inc idx) preorder))]
      (Tree. v left right))))


(comment
  (def t1 (map->Tree {:value 1
                      :left {:value 2
                             :left {:value 3 :left nil :right nil}
                             :right {:value 4 :left nil :right nil}}
                      :right {:value 5
                              :left {:value 6 :left nil :right nil}
                              :right {:value 7 :left nil :right nil}}}))

  )


;;https://app.codesignal.com/interview-practice/task/Ki9zRh5bfRhH6oBau
(defn findSubstrings-match [prev word part]
  (println "=====")
  (println "part: " part)
  (println "prev: " prev)
  (if-let [result (re-find (re-pattern (str "(.*?)(" part ")(.*)")) word)]    
    (let [[_ a b c] result
          [prev-word prev-part prev-idx] prev]
      (cond
        (> (count b) (count prev-part))
        [(str a "[" b "]" c) part (count a)]

        (and (= (count b) (count prev-part))
             (< (count a) prev-idx))
        [(str a "[" b "]" c) part (count a)]

        :else
        prev))
    
    (let [[prev-word prev-part prev-idx] prev]
      [(or prev-word word) (or prev-part "") (or prev-idx -1)])))

(defn findSubstrings [words parts]
  (map (fn [word]
         (if (empty? parts)
           word
           (first (reduce (fn [m v] (findSubstrings-match m word v))
                          [] parts)))) words))

(defn add-to-trie [trie x]
  (assoc-in trie x (merge (get-in trie x) {:val x :terminal true})))

(defn in-trie? [trie x]
  "Returns true if the value x exists in the specified trie."
  (:terminal (get-in trie x) false))

(defn prefix-matches [trie prefix]
  "Returns a list of matches with the prefix specified in the trie specified."
  (keep :val (tree-seq map? vals (get-in trie prefix))))

(defn build-trie [coll]
  "Builds a trie over the values in the specified seq coll."
  (reduce add-to-trie {} coll))


(defn deg->dec [deg min sec]
  (let [r (+ deg (/ min 60))
        r (if sec (+ r (/ sec 3600)) r)]
    r))
