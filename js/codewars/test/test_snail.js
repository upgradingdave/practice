var expect = require('chai').expect;
var assert = require('chai').assert;

const {snail} = require("../src/snail.js");

describe('Snail', function(done) {
  it('find correct snail path', function() {
    assert.deepEqual(snail([[]]), []);
    assert.deepEqual(snail([[1]]), [1]);
    assert.deepEqual(snail([[1, 2, 3], [4, 5, 6], [7, 8, 9]]), [1, 2, 3, 6, 9, 8, 7, 4, 5]);

  });
});
