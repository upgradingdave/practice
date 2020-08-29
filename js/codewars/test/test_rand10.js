var expect = require('chai').expect;
var assert = require('chai').assert;

const {rand10, expectedValue, rand7} = require("../src/rand10.js");

describe("Rand 10", function(done) {

  it('computes expected value', function() {
    expect(expectedValue(6)).to.equal(3.5);
    expect(expectedValue(10)).to.equal(5.500000000000001);
    expect(expectedValue(7)).to.equal(3.9999999999999996);

    let total = 0;
    for(let i=0; i<1000; i++) {
        total = total + rand7();
    }
    let est = total/1000;
    expect(est).to.closeTo(3.9999999999999996, 0.5);

    total = 0;
    for(let i=0; i<1000; i++) {
        let r = rand10();
        //console.log(r);
        total = total + r;
    }
    est = total/1000;
    expect(est).to.closeTo(5.500000000000001, 0.4);
  });

});
