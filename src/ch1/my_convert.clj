(ns ch1.my-convert)

(defmulti convert
  "Convert quantity from unit1 to unit2, matching on [unit1 unit2]"
  (fn [unit1 unit2 quantity] unit1 unit2))

;; lb to oz

