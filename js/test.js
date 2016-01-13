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
