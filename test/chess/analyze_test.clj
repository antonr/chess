(ns chess.analyze-test
  (:require [clojure.test :refer :all]
            [chess.analyze :refer :all]))

(deftest solves-one-step-puzzles
  (let [test-board
        [:. :. :. :. :k :. :. :.
         :. :. :. :. :. :. :. :.
         :. :. :. :. :K :. :. :.
         :. :. :. :. :. :. :. :.
         :. :. :. :. :. :. :. :.
         :. :. :. :. :. :. :. :.
         :. :. :R :. :. :. :. :.
         :. :. :. :. :. :. :. :.]]
    (is (= (analyze test-board true true 0)
           [{:move {:from [2 6] :to [2 0]}
             :responses []}]))))

(deftest solves-two-step-puzzles
    (let [test-board
          [:. :. :. :k :. :. :. :.
           :. :. :. :. :. :. :. :.
           :. :. :. :. :. :K :. :.
           :. :. :. :. :. :. :. :.
           :. :. :. :. :. :. :. :.
           :. :. :. :. :. :. :. :.
           :. :. :R :. :. :. :. :.
           :. :. :. :. :. :. :. :.]]
      (is (= (analyze test-board true true 2)
             [{:move {:from [5 2] :to [4 2]}
               :responses [{:move {:from [3 0] :to [4 0]}
                            :responses [{:move {:from [2 6] :to [2 0]}
                                         :responses []}]}]}]))))
