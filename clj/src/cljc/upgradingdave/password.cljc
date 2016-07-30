(ns upgradingdave.password
  (:require #?(:clj  [clojure.spec       :as s]
               :cljs [cljs.spec          :as s])
            #?(:clj  [clojure.spec.gen   :as gen]
               :cljs [cljs.spec.impl.gen :as gen])))

(def char-lowers (into #{} (map char (range 97 122))))
(def char-lower? char-lowers)

(s/def ::two-lowers
  (s/and string? #(<= 2 (count (filter char-lower? (into #{} %))))))

(def char-uppers (into #{} (map char (range 65 91))))
(def char-upper? char-uppers)

(s/def ::two-uppers
  (s/and string? #(<= 2 (count (filter char-upper? (into #{} %))))))

(def char-digits (into #{} (map char (range 48 58))))
(def char-digit? char-digits)

(s/def ::two-digits
  (s/and string? #(<= 2 (count (filter char-digit? (into #{} %))))))

(def char-symbols #{\! \$ \^ \&})
(def char-symbol? char-symbols)

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

(defn gen-concat [& gens]
  (gen/fmap (fn [gens] (apply concat gens))
            (apply gen/tuple gens)))


;; Here's a specification for the rules for a password generator

(def max-chars 1000)

(s/def ::max                (s/int-in 1 max-chars))
(s/def ::min                (s/int-in 0 max-chars))

(s/def ::password-class
  (s/keys :req [::class
                ::valid]))

;; TODO: password-conf spec should also define the following: 
;; max-length must be greater than min-length
(s/def ::password-conf 
  (s/keys :req [::min-length 
                ::max-length
                ::valid-char-classes]))

;; A password config defines how our password generator behaves

(def pwd-default
  {::min-length      10
   ::max-length      20
   ::valid-char-classes
   [
    {::class ::uppercase ::min 2 ::valid char-uppers}
    {::class ::lowercase ::min 2 ::valid char-lowers}
    {::class ::symbols   ::min 2 ::valid char-symbols}
    {::class ::digits    ::min 2 ::valid char-digits}]
   })

;; Now, we can create a password generator using a password config

(defn valid-chars
  [& [pwd-conf]]
  (let [new-map (merge pwd-default pwd-conf)]
    valid (filter (not (nil? %))
                  [(if (> (::min-lowercase new-map) 0) 
                     valid-lowercase)
                   (if (> (::min-uppercase new-map) 0)
                     valid-uppercase)
                   (if (> (::min-digits new-map) 0)
                     valid-digits)
                   (if (> (::min-symbols new-map) 0)
                     (::valid-symbolsbols new-map) 
                     valid-symbols)])))

(defn gen1 [& [pwd-conf]]
  (let [new-map (merge pwd-default pwd-conf)
        {min-lowercase   ::min-lowercase
         min-uppercase   ::min-uppercase
         min-digits      ::min-digits
         min-symbols     ::min-symbols
         valid-lowercase ::valid-lowercase
         valid-uppercase ::valid-uppercase
         valid-symbols   ::valid-symbols
         valid-digits    ::valid-digits
         min-length      ::min-length
         max-length      ::max-length} new-map
        gens [(if (> min-lowercase 0) 
                (gen/vector-distinct (gen/elements valid-lowercase) 
                                     {:num-elements min-lowercase}))
              (if (> min-uppercase 0)
                (gen/vector-distinct (gen/elements valid-uppercase) 
                                     {:num-elements min-uppercase}))
              (if (> min-digits 0)
                (gen/vector-distinct (gen/elements valid-digits) 
                                     {:num-elements min-digits}))
              (if (> min-symbols 0)
                (gen/vector-distinct (gen/elements valid-symbols) 
                                     {:num-elements min-symbols}))]
        rules (apply gen-concat (filter #(not (nil? %)) gens))
        ]))

