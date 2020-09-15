var expect = require('chai').expect;
var assert = require('chai').assert;

const {houseRobber} = require("../src/houseRobber.js");

describe("House Robber", function(done) {

    it('satisfies example 1', function() {
        expect(houseRobber([1,2,3,1])).to.equal(4);
    });

    it('satisfies example 2', function() {
        expect(houseRobber([2,7,9,3,1])).to.equal(12);
    });

    it('satisfies example 3', function() {
        expect(houseRobber([50,0,0,50,0])).to.equal(100);
    });

    it('satisfies example 4', function() {
        expect(houseRobber([1,1])).to.equal(1);
    });

    it('satisfies example 5', function() {
        expect(houseRobber([0])).to.equal(0);
    });

    it('satisfies example 6', function() {
        expect(houseRobber([6,3,10,8,2,10,3,5,10,5,3])).to.equal(39);
    });

    it('satisfies example 7', function() {
        expect(houseRobber([1,7,9,4])).to.equal(11);
    });

    it('satisfies example 8', function() {
        expect(houseRobber([2,4,8,9,9,3])).to.equal(19);
    });

    it('satisfies example 9', function() {
        expect(houseRobber([114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240])).to.equal(4173);
    });



});
