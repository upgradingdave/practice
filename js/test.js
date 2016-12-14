var assert = require('assert');
var euler = require('./euler');

describe('euler problem 1', function() {
  it('should sum numbers divisible by 3 or 5', function () {
    assert.equal(23,     euler.problem1(10));
    assert.equal(233168, euler.problem1(1000));
  });
});

describe('euler problem 2', function() {
  it('should sum fibonacci numbers', function () {
    assert.equal(10,      euler.problem2(10));
    assert.equal(4613732, euler.problem2(4000000));
  });
});

describe('euler problem 3', function() {
  it('should find the largest prime factor of n', function () {
    assert.equal(29,   euler.problem3(13195));
    assert.equal(6857, euler.problem3(600851475143));
  });
});

describe('euler problem 4', function() {
  it('should find palindromes', function() {
    assert.equal(true,  euler.isPalindrome(9009));
    assert.equal(true,  euler.isPalindrome(99));
    assert.equal(true,  euler.isPalindrome(90909));
    assert.equal(false, euler.isPalindrome(90019));
    assert.equal(true,  euler.isPalindrome(906609));
  });

  it('should find the largest palindrome of an n digit number', function () {
    assert.equal(9009,   euler.problem4(2));
    assert.equal(906609,  euler.problem4(3));
  });
});

describe('euler problem 5', function() {
  it('should find smallest evenly divisible numbers', function () {
    assert.equal(2520, euler.problem5(10));
    assert.equal(232792560, euler.problem5(20));
  });
});

describe('euler problem23', function() {
  it('should find non abundant numbers', function () {
    assert.deepEqual([1], euler.factors(1));
    assert.deepEqual([1, 2], euler.factors(4));
    assert.deepEqual([1,2,14,4,7], euler.factors(28));
    assert.equal(true, euler.isPerfect(4) < 0);
    assert.equal(true, euler.isPerfect(28) == 0);
    assert.equal(true, euler.isPerfect(12) > 0);
    assert.equal(4179871, euler.problem23());
  });
});

describe('factorial', function() {
  it('should find factorial', function () {
    assert.equal(1, euler.factorial(1));
    assert.equal(2, euler.factorial(2));
    assert.equal(6, euler.factorial(3));
  });
});

describe('swap', function() {
  it('should swap chars in strings', function () {
    assert.equal("1023", euler.swap("0123",0,1));
    assert.equal("3120", euler.swap("0123",0,3));
  });
});

describe('reverse', function() {
  it('should reverse string', function () {
    assert.equal("1023", euler.reverse("3201"));
    assert.equal("102", euler.reverse("201"));
    assert.equal("123456789012345678901234567890",
      euler.reverse("098765432109876543210987654321"));
  });
});

describe('euler problem24', function() {
  it('should permute', function () {
    assert.deepEqual([1,0], euler.shift([0,1]));
    assert.deepEqual([1,2,0], euler.shift([0,1,2]));

    assert.equal("10", euler.nextLexicographicalPermutation("01"));
    assert.equal("021", euler.nextLexicographicalPermutation("012"));
    assert.equal("0123456798",
      euler.nextLexicographicalPermutation("0123456789"));
    assert.equal("2701345869",
      euler.nextLexicographicalPermutation("2701345698"));

    assert.equal("0123456978",
      euler.nextLexicographicalPermutation("0123456897"));

    // assert.equal("2701345869",
    //   euler.nextLexicographicalPermutation("2701345869"));
    assert.equal(362880, euler.factorial(9));
    assert.equal(40320, euler.factorial(8));

    assert.equal("0123457968", euler.bruteNthPerm("0123456789", 10));
    assert.equal("210", euler.bruteNthPerm("012", 5));
    //assert.equal("2783915460", euler.problem24());
  });
});
