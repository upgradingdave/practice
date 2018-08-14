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
  },

  /*
   * 2520 is the smallest number that can be divided by each of the
   * numbers from 1 to 10 without any remainder.

   * What is the smallest positive number that is evenly divisible by all
   * of the numbers from 1 to 20?
   */
  problem5: function(max) {
    var i = 0;
    var found = false;
    while(!found) {
      i += max;
      var divides = true;
      for(var j=1; j <= max && divides ; j++) {
        if(i % j != 0) {
          divides = false;
        }
      }
      if(divides) return i;
    }
  },

  factors: function(n) {
    var results = new Set([1]);
    if(n==1) return Array.from(results);
    for(var i=2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        results.add(i);
        results.add(n/i);
      }
    }
    return Array.from(results);
  },

  /*
   * Returns 0 if perfect, + if abundant, - if deficient
   */
  isPerfect: function(n) {
    var fs = this.factors(n);
    var sum = 0;
    for(var j=0, len=fs.length; j<len; j++){
      sum += fs[j];
    }
    return sum - n;
  },

  /*
   * Find abundant numbers less than x
   */
  abundantNumbers: function(x) {
    let abundant = [];
    for(var i=2; i<x; i++) {
      if(this.isPerfect(i) > 0) {
        abundant.push(i);
      }
    }
    return abundant;
  },

  /*
   * Find all valid sums of abundant numbers less than x
   */
  abundantSums: function (m) {
    // initialize array of size m to all false
    let result = [];
    for(let x=0; x<m; x++) {
      result[x] = false;
    }

    // combine all possible sums of 2 abundantNums below m. Mark
    // position of array true.
    let abundantNums = this.abundantNumbers(m);
    for(let x=0;x<abundantNums.length;x++) {
      for(let y=x;y<abundantNums.length;y++) {
        let sum = abundantNums[x] + abundantNums[y];
        if(sum > m) {
          continue;
        } else {
          result[sum] = true;
        }
      }
    }
    return result;
  },

  problem23: function() {
    // find all integers which cannot be written as the sum of two abundant
    // numbers
    let abundantSum = this.abundantSums(28124);
    let sum = 0;
    for(let i=0; i<28214; i++) {
      if(abundantSum[i]==false) {
        sum = sum + i;
      }
    }
    return sum;
  },

  shift: function(arr) {
    if(arr.length <= 1) {
      return arr;
    }

    let newArray = [];
    for(let i=1; i<arr.length; i++) {
      newArray.push(arr[i]);
    }
    newArray.push(arr[0]);
    return newArray;
  },

  swap: function(s, i, j) {
    result = [];
    for(let x=0; x<s.length; x++) {
      if(x==i) {
        result = result + s[j];
      } else if(x ==j) {
        result = result + s[i];
      } else {
        result = result + s[x];
      }
    }
    return result;
  },

  factorial: function(n) {
    let result = 1;
    for(let i=n; i>0; i--) {
      result = result * i;
    }
    return result;
  },

  reverse: function(s) {
    let i=0,j=s.length-1;
    while(j>i) {
      s = this.swap(s,i,j);
      i++;
      j--;
    }
    return s;
  },

  logEnabled : false,
  log : function() {
    if(this.logEnabled && console) {
      console.log.apply(console, arguments);
    }
  },

  /*
   * What is the millionth lexicographic permutation of the digits 0, 1,
   * 2, 3, 4, 5, 6, 7, 8 and 9?
   * Find the next lexicographic permutation
   * https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
   */
  nextLexicographicalPermutation: function(s) {
    //this.logEnabled = true;
    this.log("compute next permutation");

    let len = s.length;
    let c = s.slice();

    // find the "weakly decreasing suffix". In other words scan from right to
    // left until you find a number greater than the previous number. This
    // is the index where the suffix starts
    let i=len-1;
    while(i>0 && c[i] <= c[i-1]) {
      i--;
    }

    this.log("i:"+i);

    // Set the "pivot" index to the be the index just to the left of i. If we're
    // already at the beginning index, then there are no more permutations
    let p = 0;
    if(i<=0){
      return null;
    } else {
      p = i-1;
    }

    this.log("p:"+p);

    // find the smallest right most number larger than the pivot. In other
    // words, find the smallest number in the suffix bigger than the pivot.
    for(let x=i;x<len;x++){
      if(c[x] > c[p]) {
        j = x;
      }
    }

    this.log("j:"+j);

    // swap j and p
    c = this.swap(c, j, p);

    this.log("after swap:"+c);

    // reverse the suffix
    let h=i,g=len-1;
    while(h<g) {
      c = this.swap(c,h,g);
      h++;
      g--;
    }

    this.log("after reverse: "+ c);

    return c;
  },

  /*
   * Use factorials to figure out permutations to start from
   */
  calcPermStart: function() {
    let s = "0123456789";
    let total = 1000000;
    let result = [];

    for(let i=9; i>0 && total > 0; i--) {
      let f = this.factorial(i);
      let n = Math.floor(total / f);
      result.push(n);
      total = total - (n * f);
      this.log(total);
    }
  },

  bruteNthPerm: function(start, nth) {

    for(let i=0; i<nth; i++) {
      this.log(i+"'> " + start);
      start = this.nextLexicographicalPermutation(start);
      //console.log(i+"> " + start);
    }
    return start;
  },

  problem24: function() {
    //2783915604
    return this.bruteNthPerm("0123456789", 999999);

    // TODO: non brute force method
  },
  
};
