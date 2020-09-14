var expect = require('chai').expect;
var assert = require('chai').assert;

const {insertInterval} = require("../src/insertInterval.js");

describe("Insert Interval", function(done) {

    it('satisfies example 1', function() {
        expect(insertInterval([[1,3],[6,9]], [2,5])).to.deep.equal([[1,5],[6,9]]);
    });

    it('satisfies example 2', function() {
        expect(insertInterval([[1,2],[3,5],[6,7],[8,10],[12,16]], [4,8])).to.deep.equal([[1,2],[3,10],[12,16]]);
    });

    it('satisfies example 3', function() {
        expect(insertInterval([], [5,7])).to.deep.equal([[5,7]]);
    });

    it('satisfies example 4', function() {
        expect(insertInterval([[1,5]], [6,8])).to.deep.equal([[1,5],[6,8]]);
    });

    it('satisfies example 5', function() {
        expect(insertInterval([[1,5]], [0,0])).to.deep.equal([[0,0],[1,5]]);
    });

});
