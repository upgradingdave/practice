var expect = require('chai').expect;
var assert = require('chai').assert;

const {maxProfit} = require("../src/maxProfit.js");

describe("Max Profit", function(done) {

    it('example 1', function() {
        expect(maxProfit([7,1,5,3,6,4])).to.equal(7);
    });

    it('example 2', function() {
        expect(maxProfit([1,2,3,4,5])).to.equal(4);
    });

    it('example 3', function() {
        expect(maxProfit([7,6,4,3,1])).to.equal(0);
    });


});
