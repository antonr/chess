(ns chess.fen-test
  (:require [clojure.test :refer :all]
            [chess.fen :refer :all]))

(deftest test-parse-postion
  (is (= (parse "4k3/8/4K3/8/8/8/R7/8 w ---- - 0 1") [:. :. :. :. :k :. :. :.
                                                      :. :. :. :. :. :. :. :.
                                                      :. :. :. :. :K :. :. :.
                                                      :. :. :. :. :. :. :. :.
                                                      :. :. :. :. :. :. :. :.
                                                      :. :. :. :. :. :. :. :.
                                                      :R :. :. :. :. :. :. :.
                                                      :. :. :. :. :. :. :. :.])))
