(ns ch1.my-money)

(defn validate-same-currency
  [m1 m2]
  (or (= (:currency m1) (:currency m2))
      (throw
       (ex-info "Currencies do not match."
                {:m1 m1 :m2 m2}))))
;; => #'ch1.my-money/validate-same-currency

(defrecord Currency [divisor sym desc])
;; => ch1.my_money.Currency

(defrecord Money [amount ^Currency currency]
  java.lang.Comparable
  (compareTo [m1 m2]
    (validate-same-currency m1 m2)
    (compare (:amount m1) (:amount m2))))
;; => ch1.my_money.Money

(def currencies {:usd (->Currency 100 "USD" "US Dollars")
                 :eur (->Currency 100 "EUR" "Euro")})
;; => #'ch1.my-money/currencies


(defn =$
  ([m1] true)
  ([m1 m2] (zero? (.compareTo m1 m2)))
  ([m1 m2 & monies]
   (every? zero? (map #(.compareTo m1 %) (conj monies m2)))))
;; => #'ch1.my-money/=$

(defn +$
  ([m1] m1)
  ([m1 m2]
   (validate-same-currency m1 m2)
   (->Money (+ (:amount m1) (:amount m2)) (:currency m1)))
  ([m1 m2 & monies]
   (reduce +$ (conj monies m2))))
;; => #'ch1.my-money/+$

(defn make-money
  ([] (make-money 0))
  ([amount] (make-money amount :usd))
  ([amount currency] (->Money amount currency)))
;; => #'ch1.my-money/make-money

(make-money)
;; => #ch1.my_money.Money{:amount 0, :currency :usd}

(make-money 1)
;; => #ch1.my_money.Money{:amount 1, :currency :usd}

(make-money 5 (:eur currencies))
;; => #ch1.my_money.Money{:amount 5, :currency #ch1.my_money.Currency{:divisor 100, :sym "EUR", :desc "Euro"}}
