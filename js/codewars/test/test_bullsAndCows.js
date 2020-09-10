var expect = require('chai').expect;
var assert = require('chai').assert;

const {bullsAndCows} = require("../src/bullsAndCows.js");

describe("Bulls And Cows", function(done) {

    it('satisfies example1', function() {
        expect(bullsAndCows("1807", "7810")).to.deep.equal("1A3B");
    });

    it('satisfies example2', function() {
        expect(bullsAndCows("1123", "0111")).to.deep.equal("1A1B");
    });

    it('satisfies example3', function() {
        expect(bullsAndCows("1122", "2211")).to.deep.equal("0A4B");
    });

});
