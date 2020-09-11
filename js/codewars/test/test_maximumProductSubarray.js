var expect = require('chai').expect;
var assert = require('chai').assert;

const {maximumProductSubarray} = require("../src/maximumProductSubarray.js");

describe("Maximum Product Subarray", function(done) {

    it('satisfies example 1', function() {
        expect(maximumProductSubarray([2,3,-2,4])).to.equal(6);
        expect(maximumProductSubarray([-2,0,-1])).to.equal(0);
        expect(maximumProductSubarray([0,2])).to.equal(2);
    });

    it('satisfies example 2', function(){
        expect(maximumProductSubarray([-2,3,-4])).to.equal(24);
    });

});
