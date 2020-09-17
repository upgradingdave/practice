var expect = require('chai').expect;
var assert = require('chai').assert;

const {robotBoundedInCircle} = require("../src/robotBoundedInCircle.js");

describe("Robot Bounded in Circle", function(done) {

    it('satisfies example 1', function() {
        expect(robotBoundedInCircle('GGLLGG')).to.equal(true);
    });

    it('satisfies example 2', function() {
        expect(robotBoundedInCircle('GG')).to.equal(false);
    });

    it('satisfies example 3', function() {
        expect(robotBoundedInCircle('GL')).to.equal(true);
    });

    it('satisfies example 4', function() {
        expect(robotBoundedInCircle('GGL')).to.equal(true);
    });

    it('satisfies example 5', function() {
        expect(robotBoundedInCircle('GLRLLGLL')).to.equal(true);
    });

});
