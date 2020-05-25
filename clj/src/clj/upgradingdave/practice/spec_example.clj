(ns upgradingdave.practice.spec-example
  (:require [clojure.spec     :as s]
            [clojure.spec.gen :as gen]))

(s/def ::two-lowers #(re-matches #".*[a-z]+.*[a-z]+.*" %))
(s/valid? ::two-lowers "1234")   ;;=> false
(s/valid? ::two-lowers "12b34a") ;;=> true

(def char-lower? (into #{} (map char (range 97 122))))
(s/def ::two-lowers
  (s/and string? #(<= 2 (count (filter char-lower? (into #{} %))))))
(gen/generate (s/gen ::two-lowers))

(def char-upper? (into #{} (map char (range 65 91))))
(s/def ::two-uppers
  (s/and string? #(<= 2 (count (filter char-upper? (into #{} %))))))
(s/valid? ::two-uppers "AB")        ;;=> true
(gen/generate (s/gen ::two-uppers)) ;;=> "zjxjglIn282U3HNjZg"

(def char-digit? (into #{} (map char (range 48 58))))
(s/def ::two-digits
  (s/and string? #(<= 2 (count (filter char-digit? (into #{} %))))))
(s/valid? ::two-digits "12")        ;;=> true
(gen/generate (s/gen ::two-digits)) ;;=> "fWC10nU0C80IvJ0a3fjR6Z6LVV4f"

(def char-symbol? #{\! \$ \^ \&})

;; valid char generator
(defn gen-chars [& args]
  (apply gen/vector
         (concat [(gen/one-of [(gen/elements char-lower?)
                               (gen/elements char-upper?)
                               (gen/elements char-digit?)
                               (gen/elements char-symbol?)])] args)))

(defn gen-two-symbols []
  (gen/fmap (fn [[a b]] (apply str (shuffle (concat a b))))
            (gen/tuple (gen/vector-distinct (gen/elements char-symbol?) 
                                            {:min-elements 2}) 
                       (gen-chars))))

(s/def ::two-symbols
  (s/with-gen
    (s/and string? #(<= 2 (count (filter char-symbol? (into #{} %)))))
    gen-two-symbols))

(s/valid? ::two-symbols "$!")        

;; Password
(defn gen-password []
  (gen/fmap (fn [[a b c d e]] 
              (let [;; make sure we satisfy the rules
                    rules (concat a b c d) 
                    ;; replace first chars in random string with rules
                    end   (concat rules (subvec e (- (count rules) 1)))]
                (apply str (shuffle end))))
            (gen/tuple (gen/vector-distinct (gen/elements char-lower?) 
                                            {:num-elements 2})
                       (gen/vector-distinct (gen/elements char-upper?) 
                                            {:num-elements 2})
                       (gen/vector-distinct (gen/elements char-digit?) 
                                            {:num-elements 2})
                       (gen/vector-distinct (gen/elements char-symbol?) 
                                            {:num-elements 2})
                       ;; generate between 10 and 15 chars
                       (gen-chars 10 15))))

(s/def ::10-to-15-chars (s/and string? #(s/int-in-range? 10 16 (count %))))

(s/def ::password
  (s/with-gen 
    (s/and ::two-lowers
           ::two-uppers
           ::two-digits
           ::two-symbols
           ::10-to-15-chars)
    gen-password))

(defn create-password []
  (gen/generate (s/gen ::password)))

(s/fdef create-password
        :args empty?
        :ret ::password)

(comment
  (require '[clojure.spec.test :as stest])
  (stest/instrument `create-password)
  (stest/check `create-password)  
)

