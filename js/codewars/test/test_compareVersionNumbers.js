var expect = require('chai').expect;
var assert = require('chai').assert;

const {compareVersionNumbers} = require("../src/compareVersionNumbers.js");

describe("Compare Version Numbers", function(done) {

    it('satisfies example1', function() {
        expect(compareVersionNumbers("0.1", "1.0")).to.equal(-1);
        expect(compareVersionNumbers("0.1", "0.1.0")).to.equal(0);
        expect(compareVersionNumbers("1.0.1", "1")).to.equal(1);
        expect(compareVersionNumbers("7.5.2.4", "7.5.3")).to.equal(-1);
        expect(compareVersionNumbers("1.01", "1.001")).to.equal(0);
        expect(compareVersionNumbers("1.0", "1.0.0")).to.equal(0);
        expect(compareVersionNumbers("1", "1.1")).to.equal(-1);

    });
    it('satisfies example2', function() {
        expect(compareVersionNumbers("1", "1.0.1")).to.equal(-1);
    });

});
