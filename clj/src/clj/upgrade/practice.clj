(ns upgrade.practice)

;; CodeSignal "ZigZag"

(defn zigzag-length [seq]
  (cond

    (<= (count seq) 0)
    0

    (= (count seq) 1)
    1

    (= (count seq) 2)
    (if (= (first seq) (second seq))
      1
      2)

    :else
    (reduce (fn [val [a b c]] 
              (if (or (and (< b a) (< b c)) (and (> b a) (> b c)))
                (+ val 1)
                val))
            2
            (partition 3 1 seq))))

(defn longest-zigzag [a]
  (loop [f [(first a)] r (rest a) total 1]
    (if (empty? r)
      total

      (let [n (conj f (first r))
            len (zigzag-length n)
            zig? (= (count n) len)
            ]
        (if zig?
          (if (> len total)
            (recur n (rest r) len)
            (recur n (rest r) total))
          total)))))

(defn zigzag [a]
  (loop [a a total 1]
    (if (empty? a)
      total

    (let [len (longest-zigzag a)]
      (if (> len total)
        (recur (rest a) len)
        (recur (rest a) total))))))


;; CodeSignal minesweep
(def gameboard1
  [[0, 1, 9, 1],
   [0, 1, 1, 1],
   [0, 0, 0, 0]])

(def gameboard2
  [[0,0,0,1,1], 
   [0,0,0,1,9], 
   [0,0,0,2,1], 
   [0,0,0,1,9], 
   [0,0,0,1,1]])


(defn at-coord [gameboard x y]
  (get (get gameboard x) y))

(defn check-coord [gameboard x y]
  (let [found 0
        curr (at-coord gameboard x y)
        ul   (at-coord gameboard (- x 1) (- y 1))
        found (if (= ul 9) (+ 1 found) found)
        uc   (at-coord gameboard (- x 1) (- y 0))
        found (if (= uc 9) (+ 1 found) found)        
        ur   (at-coord gameboard (- x 1) (+ y 1))
        found (if (= ur 9) (+ 1 found) found)
        l    (at-coord gameboard (- x 0) (- y 1))
        found (if (= l 9) (+ 1 found) found)
        r    (at-coord gameboard (- x 0) (+ y 1))
        found (if (= r 9) (+ 1 found) found)
        bl   (at-coord gameboard (+ x 1) (- y 1))
        found (if (= bl 9) (+ 1 found) found)
        bc   (at-coord gameboard (+ x 1) (- y 0))
        found (if (= bc 9) (+ 1 found) found)
        br   (at-coord gameboard (+ x 1) (+ y 1))
        found (if (= br 9) (+ 1 found) found)
        ]
    (or (= 9 curr) (= curr found))))

(defn minesweeper1 [gameboard]
  (let [width (count (get gameboard 0))
        height (count gameboard)]
    (reduce (fn [val item]
              (and val item))
            true
            (for [x (range height)
                  y (range width)]
              (do ;;(println x y (at-coord gameboard x y))
                (check-coord gameboard x y))))))


;; Codesignal matrix

(def matrix1 [[1, 0, 0, 2], 
              [0, 5, 0, 1], 
              [0, 0, 3, 5]])

(def rowsToDelete1 [1])
(def columnsToDelete1 [0 2])

(comment
  ;; expected output
  [[0, 2],
   [0, 5]])

(defn deleteRows [matrix rowsToDelete]
  (for [[i row] (map-indexed vector matrix)]
    (when (not (some #{i} rowsToDelete))
      row)))

(defn deleteCols [matrix colsToDelete]
  (for [row matrix]
    (for [[i col] (map-indexed vector row)
          :when (not (some #{i} colsToDelete))]
      col)))

(defn constructSubmatrix [matrix rowsToDelete columnsToDelete]
  (into [] (map #(into [] %)
                (filter #(not (nil? %))
                        (-> matrix
                            (deleteCols columnsToDelete)
                            (deleteRows rowsToDelete))))))


;; Clodesignal bankRequests
;;https://app.codesignal.com/skill-test/uwHnJdA8S6LqrjStt
(comment
  For accounts = [10, 100, 20, 50, 30] and
  requests = ["withdraw 2 10", "transfer 5 1 20", "deposit 5 20", "transfer 3 4 15"],
  the output should be bankRequests(accounts, requests) = [30, 90, 5, 65, 30].

  )

(def accounts1 [10, 100, 20, 50, 30])
(def requests1 ["withdraw 2 10", "transfer 5 1 20", "deposit 5 20", "transfer 3 4 15"])

;; invalid ids 
(def accounts2 [10, 100])
(def requests2 ["deposit 1 11"])

(def accounts3 [10, 100])
(def requests3 ["transfer 2 1 101"])

(defn parse-int [i] (try (. Integer parseInt i) (catch Exception e nil)))

(defn parse-request [request]
  (let [[cmd & xs] (clojure.string/split request #"\s")]
    (cond

      (= cmd "transfer")
      (let [[i j sum] xs]
        [cmd (parse-int i) (parse-int j) (parse-int sum)])

      (= cmd "deposit")
      (let [[i sum] xs]
        [cmd (parse-int i) (parse-int sum)])

      (= cmd "withdraw")
      (let [[i sum] xs]
        [cmd (parse-int i) (parse-int sum)])

      :else nil)))

(defn bank-error! [bank-state i]
  (swap! bank-state assoc
         :accounts [(- 0 (+ i 1))]
         :error true
         ))

(defn account-number-invalid? [account-num accounts-count]
  (or (<= account-num 0) (> account-num accounts-count)))

(defn bankRequests [accounts requests]
  (let [bank-state (atom {:error nil
                          :accounts accounts})]
    
    (doseq [[idx request-str] (map-indexed vector requests)
            :while (not (:error @bank-state))]
      (let [[cmd & xs] (parse-request request-str)]

        ;;(println "Processing (" idx ") :" request-str)
        ;;(println "bank-state: " @bank-state)

        (cond

          (= "transfer" cmd)
          (let [[i j sum] xs]
            ;; valid account number?
            (if (or (account-number-invalid? i (count (:accounts @bank-state)))
                    (account-number-invalid? j (count (:accounts @bank-state))))
              (do
                ;;(println "Account Ids Invalid!")
                (bank-error! bank-state idx))

              ;; account numbers are valid, continue with transfer
              (let [i-amount (get (:accounts @bank-state) (- i 1))
                    j-amount (get (:accounts @bank-state) (- j 1))]
                (cond
                  
                  (> sum i-amount)
                  (do
                    ;;(println "Sum larger than balance!")
                    (bank-error! bank-state idx))

                  :else
                  (do
                    ;;(println "do transfer")
                    (swap! bank-state
                           (fn [s]
                             (-> s
                                 (assoc-in [:accounts (- j 1)] (+ j-amount sum))
                                 (assoc-in [:accounts (- i 1)] (- i-amount sum)))))
                    ;;(println "new bank-state: " @bank-state)
                    )))))

          (= "withdraw" cmd)
          (let [[i sum] xs]
            ;; valid account number?
            (if (account-number-invalid? i (count (:accounts @bank-state)))
              
              (do
                ;;(println "Account Id Invalid!")
                (bank-error! bank-state idx))

              ;; account numbers are valid, continue with withdraw
              (let [i-amount (get (:accounts @bank-state) (- i 1))]
                (cond
                  
                  (> sum i-amount)
                  (do
                    ;;(println "Sum is larger than balance!")
                    (bank-error! bank-state idx))

                  :else
                  (do
                    ;;(println "do withdraw")
                    (swap! bank-state assoc-in [:accounts (- i 1)] (- i-amount sum))
                    ;;(println "new bank-state: " @bank-state)
                    )))))

          (= "deposit" cmd)
          (let [[i sum] xs]
            ;; valid account number?
            (if (account-number-invalid? i (count (:accounts @bank-state)))
              
              (do
                ;;(println "Account Id Invalid!")
                (bank-error! bank-state idx))

              ;; account numbers are valid, continue with deposit
              (let [i-amount (get (:accounts @bank-state) (- i 1))]
                (do
                  ;;(println "do deposit")
                  (swap! bank-state assoc-in [:accounts (- i 1)] (+ i-amount sum))
                  ;;(println "new bank-state: " @bank-state)
                  ))))                

          :else
          (bank-error! bank-state idx))))
    (:accounts @bank-state)))


;; codesignal treebottom
(def tree1 "(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))")

(defn parse-tree-str [tree-str]
  (let [s (atom {:level 0
                 :max 0
                 :result {}
                 :number ""})]
    (doseq [n tree-str]
      (let [{:keys [level max number]} @s
            next-level (inc level)]
        ;;(print (str n " ---> "))
        ;;(println @s)
        (cond
          
          (= \( n)
          (do
            (swap! s assoc-in [:level] next-level)
            (when (> next-level max)
              (swap! s assoc-in [:max] next-level)))
          
          (= \) n)
          (swap! s update-in [:level] dec)

          (= \space n)
          (if (re-matches #"\d+" number)
            (swap! s (fn [o] (-> o
                                 (update-in [:result level] conj (Integer. number))
                                 (assoc-in [:number] ""))))
            (swap! s assoc-in [:number] ""))

          :else
          (swap! s update-in [:number] str n))))

    (let [{:keys [result]} @s]
      (reverse (into [] (get result (apply max (keys result))))))))

(defn treeBottom [tree]
  (parse-tree-str tree))

;; Codesignal isLucky
;; https://app.codesignal.com/skill-test/3AdBC97QNuhF6RwsQ
;; For n = 1230, the output should be
;;isLucky(n) = true;

(defn isLucky [n]
  (let [s (str n)
        c (count s)
        l (map #(Integer. (str %)) (seq s))
        [front back] (partition (/ c 2) l)]
    (= (reduce + front) (reduce + back))))

;; Codesignal firstDuplicate
;;https://app.codesignal.com/interview-practice/task/pMvymcahZ8dY4g75q
;;For a = [2, 1, 3, 5, 3, 2], the output should be
;;firstDuplicate(a) = 3.

(defn firstDuplicate [a]
  (loop [front {(first a) 1} next (first (rest a)) remain (rest (rest a)) idx 2]
    ;;(println idx " --> " next ", " front)
    (if (nil? next)
      -1
      (if (contains? front next)
        next
        (recur (assoc front next idx) (first remain) (rest remain) (inc idx))))))

;;Codesignal firstNotRepeatingCharacter
;;For s = "abacabad", the output should be
;;firstNotRepeatingCharacter(s) = 'c'.

(defn firstNotRepeatingCharacter
  "Given a string s, find and return the first instance of a
  non-repeating character in it. If there is no such character, return
  '_'."
  [s]
  (loop [coll s results {} idx 0]
    (let [[x & xs] coll]
      ;;(println x)
      (if (nil? x)
        (let [result (filter (fn [[_ [idx n]]] (= 1 n)) results)]
          (if (empty? result)
            \_
            (let [[k _] (reduce (fn [[k1 [idx1 n1]] [k2 [idx2 n2]]]
                                  (if (< idx1 idx2) [k1 [idx1 n1]] [k2 [idx2 n2]]))
                                result)]
              k)))
        (if (get results x)
          (recur xs (update results x (fn [[idx n]] [idx (inc n)]) ) (inc idx))
          (recur xs (assoc results x [idx 1]) (inc idx)))))))

;;Codesignal decodedString
;;https://app.codesignal.com/skill-test/dYCH8sdnxGf5aGkez
;;s = "z1[y]zzz2[abc]", the output should be
;;decodeString(s) = "zyzzzabcabc"
(defn decodeString1 [n s]
  ;;(println "decodeString: n: " n ", s: " s )
  (loop [x (first s) xs (rest s) result "" st nil acc nil digit nil n n brackets 0]
    ;;(println "x: " x)
    ;;(println "xs: " xs)
    ;;(println "result: " result)
    ;;(println "st: " st)
    ;;(println "acc: " acc)
    ;;(println "digit: " digit)
    ;;(println "n: " n)
    ;;(println "brackets: " brackets)
    ;;(println "-----")

    (cond

      (nil? x)
      (do
        ;;(println "decodeString Result:" (apply str (repeat n (str result acc))))
        (apply str (repeat n (str result acc))))

      ;; parse mode - just accumulate until we find the next right bracket
      (= st :parse)
      (cond
        
        (= x \[)
        (recur (first xs) (rest xs) result st (str acc x) digit n (inc brackets))
        
        (= x \])
        (if (<= brackets 0)
          (recur (first xs) (rest xs) result nil (decodeString1 (Integer. digit) acc) nil n brackets)
          (recur (first xs) (rest xs) result st (str acc x) digit n (dec brackets)))
        
        :else
        (recur (first xs) (rest xs) result st (str acc x) digit n brackets))

      ;; digit mode - accumulate digits until we find next left bracket,
      ;; then switch to parse mode
      (= st :digit)
      (cond
        (= x \[)
        (recur (first xs) (rest xs) result :parse acc digit n brackets)

        :else
        (recur (first xs) (rest xs) result st acc (str digit x) n brackets))

      ;; back to normal mode
      :else

      (cond
        (re-matches #"[a-zA-Z]+" (str x))
        (recur (first xs) (rest xs) result st (str acc x) digit n brackets)

        ;; change to parse mode
        (= \[ x)
        (recur (first xs) (rest xs) result :parse acc digit n brackets)

        ;; change to digit parsing mode
        (re-matches #"\d+" (str x))
        (recur (first xs) (rest xs) (str result acc) :digit nil (str digit x) n brackets)))))

(defn decodeString [s]
  (decodeString1 1 s))


;;https://app.codesignal.com/skill-test/cSs5ucjzWqSoD6Dbu
;;codeSignal digitRoog
(defn digitRootLt [d1 d2]
  (let [s1 (reduce + (map #(Integer. %) (map str (seq (str d1)))))
        s2 (reduce + (map #(Integer. %) (map str (seq (str d2)))))]
    (if (= s1 s2)
      (< d1 d2)
      (< s1 s2))))

(defn digitRootSort [a]
  (sort digitRootLt a))

;;Codesignal constructArray
;; https://app.codesignal.com/skill-test/9h4hjB3u4F7FNvBPL

(defn constructArray [size]
  (into []
        (take size 
              (reduce (fn [m idx]
                        (conj m (+ idx 1) (- size idx))) [] (range size)))))


;; Codesignal Tournament
;;https://app.codesignal.com/tournaments/JmxhT6Z8wZxC9ew6q/A
(defn checkSameElementExistence [arr1 arr2]
  (if (some (into #{} arr1) arr2)
    true
    false))

(defn equationTemplate [values] 
  (let [[a b c d] values]
    (or (= a (* b c d))
        (= (* a b) (* c d))      
        (= (* a b c) d))))

;; Codesignal changeRoot
;; https://app.codesignal.com/skill-test/Wa7CukAPPkoPpbsFH
;; Ex
;; [0 0 0 1]
;; parent[0] = 0 (this is the root because parent[v] = v
;; parent[1] = 0 (so node 0 is parent of node 1)
;; parent[2] = 0 (so node 0 is parent of node 2)
;; parent[3] = 1 (so node 1 is parent of node 3)
;; if newRoot = 1
;; parent[0] = 1
;; parent[1] = 1
;; parent[2] = 0
;; parent[3] = 1
;; ???? I skipped this one because rebalancing the tree seemed arbitrary?

;; Codesignal periodicSequence
;;https://app.codesignal.com/skill-test/rYiNGN925SRvbTmec
;; s[i] = (mod (+ (* a s[i-1]) b) m)
;; s[i] = s[i + T]

(defn nextPeriodicSequence [s a b m]
  (mod (+ (* a s) b) m))

(defn periodicSequence' [s a b m]
  (lazy-seq (cons s (periodicSequence' (nextPeriodicSequence s a b m) a b m))))

(defn periodicSequence [s a b m]
  (loop [s s acc []]
    (if (some #{s} acc)
      (-  (count acc) (.indexOf acc s))
      (recur (nextPeriodicSequence s a b m) (conj acc s)))))


;; Codesignal textJustification
;;https://app.codesignal.com/skill-test/rak3HBvHDAjHRkTCW
(defn textJustificationLines
  "Parse array of words, placing as many words that can fit with single
  spaces into lines of length l"
  [words l]
  (loop [words words line "" result [] t 0]
    (let [x (first words)
          xs (rest words)
          w (str x " ")]
      ;;(println "w: " w)
      ;;(println "line: " line)
      ;;(println "t: " t)
      ;;(println "result: " result)
      ;;(println "----")
      (if (nil? x)
        (if (> (count line) 0)
          (conj result line)
          result)

        (let [t (+ t (count w))
              fit? (or (<= t l) (= t (+ l 1)))]
          (if fit?
            (recur xs (str line w) result t)
            (recur xs w (conj result line) (count w))))))))

(defn textJustification [words l]
  (let [lines (textJustificationLines words l)]
    (for [[idx1 line] (map-indexed vector lines)]
      (let [words (clojure.string/split line #"\s+")
            t (reduce + (map count words))
            number-words (count words)
            spaces-needed (- l t)]
        (if (< idx1 (- (count lines) 1))
          (if (> number-words 1)
            (.trim
             (let [left-over (mod spaces-needed (- number-words 1))]
               (reduce
                (fn [m [idx w]]
                  (if (< idx left-over)
                    (str m w
                         (apply str (repeat (quot spaces-needed
                                                  (- number-words 1)) " "))
                         " ")
                    (str m w
                         (apply str (repeat (quot spaces-needed
                                                  (- number-words 1)) " ")))))
                ""
                (map-indexed vector words))))

            ;; one word lines
            (str (first words) (apply str (repeat spaces-needed " "))))

          ;; last line
          (subs 
           (str (apply str (interpose " " words))
                (apply str (repeat (- spaces-needed (- (count words) 1)) " ")))
           0 l
           ))))))

;; codesignal.com zigzagWords
;;https://app.codesignal.com/challenge/tu9KcxX573X9qFxfN
(defn zigzagWordLevels [t l]
  (loop [t t level 0 acc {} dir true]
    (let [x (first t)
          xs (rest t)]
      (if (nil? x)
        acc

        (if dir
          ;; going up
          (if (< level (- l 1))
            (recur xs (inc level) (update acc level conj x) dir)
            ;; reached the top
            (recur xs (dec level) (update acc level conj x) false))

          ;; going down
          (if (> level 0)
            (recur xs (dec level) (update acc level conj x) dir)
            ;; reached the top
            (recur xs (inc level) (update acc level conj x) true)))))))

(defn zigzagWords [t l]
  (let [t (.trim (clojure.string/replace t #"\s+" ""))]
    (if (> l 1)
      (apply str
             (reverse
              (apply str
                     (map #(apply str %)
                          (for [n (range l)]
                            (get (zigzagWordLevels t l) n))))))
      t
      )))


;; codesignal
;; https://app.codesignal.com/tournaments/M4TTEk7Kb3WB46vFz/A

(defn extraNumber [a b c]
  (cond (= a b) c
        (= a c) b
        (= c b) a))

(defn isoscelesTriangle [sides]
  (let [[a b c] sides]
    (or (= a b) (= a c) (= c b))))

;; codesignal.com
;; Daily Challenge
;; https://app.codesignal.com/challenge/8FdeLisamv6cFZPAc
(defn smallestProduct1
  "This was my first brute force attempt, but it wasn't fast enough"
  [arr]
  (reduce
   (fn [a b] (if (< a b) a b))
   (let [c (count arr)]
     (for [i (range c)
           j (range 1 c)
           k (range 2 c)
           :when (and  (not (or (= i j) (= j k) (= i k)))
                       (< i j k))]
       (let [x (get arr i)
             y (get arr j)
             z (get arr k)]
         (* x y z))))))

(defn smallestProduct [arr]
  (let [v (into [] (sort arr))
        c (count v)]
    (min
     ;; 3 smallest
     (* (get v 0) (get v 1) (get v 2))
     ;; 2 largest and smallest
     (* (get v 0) (get v (- c 1)) (get v (- c 2)))
     ;; 2 smallest and larges
     (* (get v 0) (get v 1) (get v (- c 1)))
     )))

;; CodeSignal rotateImage
;;https://app.codesignal.com/interview-practice/task/5A8jwLGcEpTPyyjTB
(defn rotateImage [a]
  (let [c (count a)]
    (for [i (range c)]
      (for [j (range c)]
        (get (get a (- c j 1)) i)))))

;; https://app.codesignal.com/tournaments/CLJobS3LnnddzhqwG/C
(defn htmlEndTagByStartTag [startTag]
  (str "</" (first (clojure.string/split
                   (second (re-find #"<([^>]+)" startTag)) #"\s")) ">"))


(defn alternatingSums [a]
  (reduce (fn [[x y] [i j]] [(+ i (or x 0)) (+ y (or j 0))]) [0 0] (partition 2 2 nil a)))

(defn divideAsLongAsPossible [n d]
  (loop [n n d d]
    (if (= (mod n d) 0)
      (recur (/ n d) d)
      n)))

(defn extraNumber [a b c]
  (cond (= a b) c
        (= b c) a
        (= a c) b)
  )

(defn evenNumbersBeforeFixed [sequence fixedElement]
  (if (contains? (into #{} sequence) fixedElement)
    (count (filter identity (for [s sequence :while (not (= s fixedElement))]
                              (when (even? s) s)
                              )))
    -1
    ))

(defn nearestRoundNumber [value]
  (loop [v value]
    (if (= (mod v 10) 0)
      v
      (recur (inc v)))))

(defn differentValuesInMultiplicationTable2 [n m]
  (count (into #{}
               (for [i (range 1 (inc n))
                     j (range 1 (inc m))]
                 (* i j)))))

(defn sudoku2-lkup [a x y] (get (get a y) x))

(defn sudoku2-row-check [r]
  (let [r (filter identity r)]
    (loop [x (first r) xs (rest r) m {}]
      (if (nil? x) true
          (if (get m x)
            false
            (recur (first xs) (rest xs) (assoc m x true)))))))

(defn sudoku2 [a]
  (let [squares (for [i (range 0 (count a) 3)
                      j (range 0 (count a) 3)]
                  
                  (sudoku2-row-check
                   (filter #(not (= \. %))
                           (for [k (range i (+ i 3))
                                 l (range j (+ j 3))]
                             (sudoku2-lkup a k l)))))

        columns (for [i (range (count a))]
                  (sudoku2-row-check
                   (filter #(not (= \. %))
                           (for [j (range (count a))]
                             (sudoku2-lkup a i j)))))
        rows (for [i a]
               (sudoku2-row-check (filter #(not (= \. %)) i)))]

    (and true
         (reduce #(and %1 %2) true squares)
         (reduce #(and %1 %2) true columns)
         (reduce #(and %1 %2) true rows))))

;;https://app.codesignal.com/challenge/dHjGPiEYFFcrwKMip
;; TODO use a stack!
(defn brothersInTheBarRound? [coll]
  (let [t (take 3 coll)] (and (= (count t) 3) (apply = t))))

(defn brothersInTheBar [glasses]
  ;;(println "here we go: " glasses)
  (if (< (count glasses) 3) 0

      (loop [f '() xs glasses r 0]

        (if (empty? xs) ;; processed the whole list, we're done
          (if (brothersInTheBarRound? f) (inc r) r)

          ;; else keep processing
          (if (brothersInTheBarRound? f) ;; found a round
            (recur (drop 3 f) xs (inc r))

            ;; else no round, try again
            (recur (cons (first xs) f) (rest xs) r))))))

;; https://app.codesignal.com/challenge/ohAu35T4j2mfvwoxk

;;http://codeforces.com/api/{methodName}
;;key: 9ad0b0a742dfd16900cc6089eea427dce9a6cc71 
;;secret: 7e5f01a6dfec312ae61f3639951c0401a7c4cb88

;;/contest.standings?contestId=464&from=1&count=5
;; (keys (first (:rows (:result (:body results)))))
;;(:party :rank :points :penalty :successfulHackCount :unsuccessfulHackCount :problemResults)

(defn now [] (java.util.Date. ))

;;(defn inst-ms [inst] (.getTime ^java.util.Date inst))

(def notTheBestFan-timer (atom {:c 0 :l (now)}))

(defn notTheBestFan-reset! []
  (let [{:keys [c l]} @notTheBestFan-timer
        n (now)
        millis (- (inst-ms n) (inst-ms l))
        t (- 1000 millis)]
    (if (< t 0)
      identity
      (Thread/sleep t))
    (reset! notTheBestFan-timer {:c 0 :l (now)})))

(defn notTheBestFan-update! []
  (let [{:keys [c l]} @notTheBestFan-timer
        n (now)
        millis (- (inst-ms n) (inst-ms l))]
    (if (< c 5)
      (swap! notTheBestFan-timer
             (fn [o] (-> o
                         (update-in [:c] inc)
                         (assoc-in [:l] (now)))))
      (notTheBestFan-reset!))))

(def notTheBestFan-base-url "http://codeforces.com/api")

(defn notTheBestFan-request [contest]
  (let [url (str notTheBestFan-base-url
                 "/contest.standings?contestId=" contest
                 "&from=1"
                 "&count=25" )
        response (slurp url)]
    (re-seq #"\"handle\":\"([^\"]+)\"\}\],\"participantType\":\"[^\"]+\",\"ghost\":[^,]+,\"room\":[^,]+,\"startTimeSeconds\":[^}]+},\"rank\":([^,]+)" response)))

(defn notTheBestFan-processRequest [m handle1 handle2]
  (loop [x (first m) xs (rest m) results {}]
    (if (nil? x)
      results
      (let [[_ handle rank] x            
            rank (Integer. rank)
            results (if (= handle handle1)
                      (assoc-in results [:rank1] rank)
                      results)
            results (if (= handle handle2)
                      (assoc-in results [:rank2] rank)
                      results)
            results (if (= rank 1)
                      (assoc-in results [:handle] handle)
                      results)]
        ;; (println "x: " x)
        ;; (println "handle: " handle)
        ;; (println "handle: " rank)
        ;; (println "results: " results)
        (recur (first xs) (rest xs) results)))))

(defn notTheBestFan [contests your-fav]
  (apply
   - 
   (loop [contest (first contests)
          contests (rest contests)
          his-fav your-fav results
          [0 0]]
     (if (nil? contest)
       results
       (let [m (notTheBestFan-request contest)
             {:keys [rank1 rank2 handle]}
             (notTheBestFan-processRequest m your-fav his-fav)
             rank1 (or rank1 25)
             rank2 (or rank2 25)
             [my-old his-old] results]
         ;; (println "contest: " contest)
         ;; (println "your-fav: " your-fav)
         ;; (println "his-fav: " his-fav)
         (recur (first contests)
                (rest contests)
                handle
                [(+ my-old rank1) (+ his-old rank2)]))))))


;; https://app.codesignal.com/interview-practice/task/yM4uWYeQTHzYewW9H
(defn isCryptSolution-lead-zero? [digit-str]
  (and (> (count digit-str) 1) (= (first digit-str) \0)))

(defn isCryptSolution [crypt solution]
  (let [m (into {} solution)
        [a b c] (for [word crypt]
                  (apply str
                         (map (fn [w] (get m w \0)) word)))
        zeros? (or (isCryptSolution-lead-zero? a)
                   (isCryptSolution-lead-zero? b)
                   (isCryptSolution-lead-zero? c))]
    (and (not zeros?) (= (bigint c) (+ (bigint a) (bigint b))))))


;; https://app.codesignal.com/skill-test/v3uf4PGocp2CH62nn
(defn wordBoggle-get [board [x y]]
  (get (get board x) y))

(defn wordBoggle-neighbors [[x y]]
  [[x (dec y)]
   [x (inc y)]
   [(dec x) (dec y)]
   [(dec x) y]
   [(dec x) (inc y)]
   [(inc x) (dec y)]
   [(inc x) y]
   [(inc x) (inc y)]])

(defn wordBoggle-check-coord [board coord letter letters searched]
  (let [l (wordBoggle-get board coord)
        matched (= l letter)]
    (if matched

      (if (nil? (first letters))
        ;; we found the word!
        true
        
        ;; else keep searching
        (let [;; find neighbor coords, remove any we've already searched
              nbors (filter (fn [c] (not (contains? searched c)))
                            (wordBoggle-neighbors coord))]
          #(loop [c (first nbors) cs (rest nbors)]
             (if (nil? c)
               false
               (if (trampoline wordBoggle-check-coord board
                               c
                               (first letters)
                               (rest letters)
                               (conj searched coord))
                 true
                 (recur (first cs) (rest cs)))))))
      ;; not a match
      false)))

(defn wordBoggle-search [board word]
  (let [coords (for [x (range (count board))
                     y (range (count (first board)))] [x y])]

    ;;iterate over coords of the board, trying to find word
    (loop [c (first coords) cs (rest coords)]
      (if (nil? c) false
          (if (trampoline wordBoggle-check-coord
                          board c (first word) (rest word) #{})
            true
            (recur (first cs) (rest cs)))))))

(defn wordBoggle [board words]
  (sort (filter (fn [word] (wordBoggle-search board word)) words)))

;;https://app.codesignal.com/tournaments/ZdfGkSxBvSCy9siYd/D
(defn depositProfit [deposit rate threshold]
  (let [r (if (= rate 100) 2 (/ rate 100))]
    (loop [d deposit cnt 0]
      (if (> d threshold)
        cnt
        (recur (+ d (* (/ rate 100) d)) (inc cnt))))))

;; int[] fractionSum(int[] a, int[] b) {

;;   class Helper {
;;     int gcdEuclid(int a, int b) {
;;       if (a == 0) {
;;         return b;
;;       }
;;       return gcdEuclid(b % a, a);
;;     }
;;   }
;;   Helper h = new Helper();

;;   int[] c = {a[0] * b[1] + a[1] * b[0], a[1] * b[1]};
;;   int gcd = h.gcdEuclid(c[0], c[1]);

;;   c[0] = c[0] / gcd;
;;   c[1] = c[1] / gcd;

;;   return c;
;; }

(defn gcd [a b]
  (loop [a1 (max a b) b1 (min a b)]
    (if (= 0 a1)
      b1
      (recur (mod b1 a1) a1))))

(defn coprime? [a b]
  (= 1 (gcd a b)))

(defn eulersTotientFunction [n]
  (loop [i 1 cnt 0]
    (if (> i n)
      cnt
      (if (coprime? n i)
        (recur (inc i) (inc cnt))
        (recur (inc i) cnt)))))

(defn sequenceElement [a n]
  (if (<= n 4)
    (get a n)
    (let [[a0 a1 a2 a3 a4] a
          a5 (mod (+ a0 a1 a2 a3 a4) 10)]
      (loop [i 5 [a0 a1 a2 a3 a4] [a1 a2 a3 a4 a5]]
        (if (>= i n)
          a4
          (recur (inc i) [a1 a2 a3 a4 (mod (+ a0 a1 a2 a3 a4) 10)]))))))


;; Definition for singly-linked list:
(defrecord ListNode [value next])

;; (defprotocol SinglyLinkedListNode
;;   (add-node [curr new-node]))

;; (extend-type ListNode
;;   SinglyLinkedListNode
;;   (add-node [curr new-node]
;;     (assoc curr :next new-node)))

;; (defn add-node [^ListNode curr v]
;;   (if curr
;;     (assoc curr :next (add-node (:next curr) v))
;;     (ListNode. v nil)))

;; (def result
;;   (loop [l nil i 0]
;;     (if (< i 100000)
;;       (recur (add-node l i) (inc i))
;;       l)))

;; (defn remove-node-by-val' [^ListNode prev ^ListNode curr v]
;;   (if curr
;;     (if prev
      
;;       ;; this is a node in the middle or end of list
;;       (if (= v (:value curr))
;;         ;; skip the curr node
;;         #(remove-node-by-val' prev (:next curr) v)
;;         ;; continue
;;         #(assoc curr :next
;;                 (remove-node-by-val' curr (:next curr) v)))

;;       ;; this is the head node
;;       (if (= v (:value curr))
;;         #(remove-node-by-val' nil (:next curr) v)
;;         #(assoc curr :next
;;                 (remove-node-by-val' curr (:next curr) v))))
;;     ;; we're done
;;     curr))

;; (defn remove-node-by-val [^ListNode head v]
;;   (trampoline remove-node-by-val' nil head v))

;; (defn add-node [^ListNode curr v]
;;   (if curr
;;     (assoc curr :next (add-node (:next curr) v))
;;     (ListNode. v nil)))

(defn listNode->vec [^ListNode l]
  (let [acc (atom [])]
    (loop [l l]
      (if (nil? l)
        @acc
        (do
          (swap! acc conj (:value l))
          (recur (:next l)))))))

(defn vec->listNode [v]
  (let [l (atom nil)]
    (doseq [n (reverse v)] (reset! l (ListNode. n @l)))
    @l))

(defn removeKFromList [l k]
  (vec->listNode (filter #(not (= k %)) (listNode->vec l))))



    
