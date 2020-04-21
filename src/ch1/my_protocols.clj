(ns ch1.my-protocols
  (:require [ch1.validate]
            [ch1.money :refer (+$ zero-dollars)])
  (:import [ch1.validate Recipe Ingredient]))

(defrecord Store [,,,])

(defn cost-of [store ingredient] ,,,)

(defprotocol Cost
  (cost [entity store]))

(extend-protocol Cost
  Recipe
  (cost [recipe store]
    (reduce +$ zero-dollars
            (map #(cost % store) (:ingredients recipe))))
  Ingredient
  (cost [ingredient store]
    (cost-of store ingredient)))


(defprotocol TaxedCost
  (taxed-cost [entity store]))

(extend-protocol TaxedCost
  Object
  (taxed-cost [entity store]
    (if (satisfies? Cost entity)
      (do (extend-protocol TaxedCost
            (class entity)
            (taxed-cost [entity store]
              (* (cost entity store) (+ 1 (tax-rate store)))))
          (taxed-cost entity store))
      (assert false (str "Unhandled entity: " entity)))))
