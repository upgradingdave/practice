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
  }

};
