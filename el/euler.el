(defun problem1 (n)
  "If we list all the natural numbers below 10 that are multiples of 3
  or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23. Find
  the sum of all the multiples of 3 or 5 below 1000."
  (let ((result 0)
        (n (- n 1)))
    (while (> n 0)
      (if (or (= (mod n 3) 0) (= (mod n 5) 0))
          (setq result (+ result n)))
      (setq n (- n 1)))
    result))

(ert-deftest problem1-test ()
  "Project Euler Problem 1"
  (should (equal 23     (problem1 10)))
  (should (equal 233168 (problem1 1000))))

(defun problem2 (maximum)
  "Each new term in the Fibonacci sequence is generated by adding the
  previous two terms. By starting with 1 and 2, the first 10 terms
  will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ... By considering the
  terms in the Fibonacci sequence whose values do not exceed four
  million, find the sum of the even-valued terms."
  )


