var expect = require('chai').expect;
var assert = require('chai').assert;

const {fizzBuzz} = require("../src/fizzbuzz.js");

describe("FizzBuzz", function(done) {

  it('replaces numbers with Fizz and Buzz', function() {
    expect(fizzBuzz(15)).to.deep.equal([
        "1",
        "2",
        "Fizz",
        "4",
        "Buzz",
        "Fizz",
        "7",
        "8",
        "Fizz",
        "Buzz",
        "11",
        "Fizz",
        "13",
        "14",
        "FizzBuzz"
    ]);
  });

});
