var expect = require('chai').expect;
var assert = require('chai').assert;

const {mincostTickets} = require("../src/mincostTickets.js");

describe("Mincost Tickets", function(done) {

  it('returns an array of numbers', function() {
    expect(mincostTickets([1,4,6,7,8,20], [2,7,15])).to.equal(11);
    expect(mincostTickets([1,2,3,4,5,6,7,8,9,10,30,31], [2,7,15])).to.equal(17);
  });

});
