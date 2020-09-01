var expect = require('chai').expect;
var assert = require('chai').assert;

const {largestTimeFromDigits} = require("../src/largestTimeFromDigits.js");

describe("Largest Time From Digits", function(done) {

  it('finds the largest time from digits', function() {
    expect(largestTimeFromDigits([1,2,3,4])).to.deep.equal("23:41");
    expect(largestTimeFromDigits([1,9,6,0])).to.deep.equal("19:06");
  });

});
