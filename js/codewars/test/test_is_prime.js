var expect = require('chai').expect;
var assert = require('chai').assert;

const {isPrime} = require("../src/is_prime.js");

describe('Is Prime?', function(done) {
  it('Basic tests', function() {
    assert.equal(isPrime(0),  false, "0 is not prime");
    assert.equal(isPrime(1),  false, "1 is not prime");
    assert.equal(isPrime(2),  true, "2 is prime");
    assert.equal(isPrime(73), true, "73 is prime");
    assert.equal(isPrime(75), false, "75 is not prime");
    assert.equal(isPrime(-1), false, "-1 is not prime");
  });

  it('Test prime', function() {
    assert.equal(isPrime(3),  true, "3 is prime");
    assert.equal(isPrime(5),  true, "5 is prime");
    assert.equal(isPrime(7),  true, "7 is prime");
    assert.equal(isPrime(41), true, "41 is prime");
    assert.equal(isPrime(5099), true, "5099 is prime");
  });

  it('Test not prime', function() {
    assert.equal(isPrime(4),  false, "4 is not prime");
    assert.equal(isPrime(6),  false, "6 is not prime");
    assert.equal(isPrime(8),  false, "8 is not prime");
    assert.equal(isPrime(9), false, "9 is not prime");
    assert.equal(isPrime(45), false, "45 is not prime");
    assert.equal(isPrime(-5), false, "-5 is not prime");
    assert.equal(isPrime(-8), false, "-8 is not prime");
    assert.equal(isPrime(-41), false, "-41 is not prime");
    assert.equal(isPrime(589566961), false, "589566961 is not prime");

  });

});
