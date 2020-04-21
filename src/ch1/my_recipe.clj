(ns ch1.my-recipe)

(defrecord Recipe
    [name          ;; string
     author        ;; recipe creator
     description   ;; string
     ingredients   ;; list of ingredients
     steps         ;; sequence of string
     servings      ;; number of servings
     ])

(defrecord Person
    [fname  ;; first name
     lname  ;; last name
     ])

(def toast
  (->Recipe
   "Toast"
   (->Person "Alex" "Miller") ;; nested
   "Crispy bread"
   ["Slice of bread"]
   ["Toast bread in toaster"]
   1))
