
module.exports = {

  /**
   * Sum numbers divisible by 3 or 5
   */
  problem1: function(n) {
    var result = 0;
    for(i=0; i<n; i++) {
      if(i % 3 == 0 || i % 5 ==0)
        result += i;
    }
    return result;
  },

  /**
   * Sum fibonacci numbers
   */
  problem2: function(max) {
    var result = 0;
    var prev1 = 0;
    var prev2 = 1;
    var curr = prev1 + prev2;
    while (curr < max) {

      if(curr % 2 == 0)
        result += curr;

      prev1 = prev2;
      prev2 = curr;
      curr = prev1 + prev2;

    }
    return result;
  },

  /**
   * Sum fibonacci numbers
   */
  problem3: function(n) {
    var p = 2;
    var x = n;

    while(x !== p) {
      if(x % p == 0) {
        x = x / p;
        p = 2;
      } else {
        p = p + 1;
      }
    }
    return x;
  },

  isPalindrome: function(n){
    if(isNaN(n))
      return false;

    var s = n.toString();
    var j = s.length-1;

    for(var i=0; i<=j; i++) {
      if(s[i] != s[j]) {
        return false;
      }
      j = j-1;
    }

    return true;
  },

  /*
   * A palindromic number reads the same both ways. The largest
   * palindrome made from the product of two 2-digit numbers is 9009 =
   * 91 × 99.
   *
   * Find the largest palindrome made from the product of two 3-digit
   *  numbers. 
   */
  problem4: function(n) {
    var s = "";
    for(var i=0; i<n; i++)
      s += "9";

    var biggest = 0;
    for(var i=parseInt(s); i>0; i--) {
      for(var j=parseInt(s); j>0; j--) {
        var guess = i*j
        if(this.isPalindrome(guess) && guess > biggest)
          biggest = guess;
      }
    }

    return biggest;
  }

};
