var expect = require('chai').expect;
var assert = require('chai').assert;

const {containsNearbyAlmostDuplicate} = require("../src/containsNearbyAlmostDuplicate.js");

describe("Contains Nearby Almost Duplicate", function (done) {

    it('satisfies examples', function () {
        //expect(containsNearbyAlmostDuplicate([1, 2, 3, 1], 3, 0)).to.deep.equal(true);
        expect(containsNearbyAlmostDuplicate([1, 0, 1, 1], 1, 2)).to.deep.equal(true);
        //expect(containsNearbyAlmostDuplicate([1, 5, 9, 1, 5, 9], 2, 3)).to.deep.equal(false);
    });

});
