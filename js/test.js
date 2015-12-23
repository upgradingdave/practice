var assert = require('assert');
var euler = require('./euler');

describe('euler problem 1', function() {
  it('should sum numbers divisible by 3 or 5', function () {
    assert.equal(23,     euler.problem1(10));
    assert.equal(233168, euler.problem1(1000));
  });
});
