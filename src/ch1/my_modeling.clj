(ns ch1.my-modeling)

(def earth {:name       "Earth"
            :moons      1
            :volume     1.08321e12 ;; km^ 3
            :mass       5.97219e24 ;; kg
            :aphelion   152098232  ;; km, farthest from sun
            :perihelion 147098290  ;; km, closest from sun
            :type       :Planet
            })


(defrecord Planet [name
                   moons
                   volume
                   mass
                   aphelion
                   perihelion])


;; Positional factory function
(def earth
  (->Planet "Earth" 1 1.08321e12 5.97219e24 152098232 147098290))

(def earth
  (map->Planet {:name       "Earth"
                :moons      1
                :volume     1.08321e12 ;; km^ 3
                :mass       5.97219e24 ;; kg
                :aphelion   152098232  ;; km, farthest from sun
                :perihelion 147098290  ;; km, closest from sun
                }))
