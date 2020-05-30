var expect = require('chai').expect;
var assert = require('chai').assert;

const {duplicateEncode} = require("../duplicate_encoder.js");

describe('Duplicate Encoder', function(done) {
  it('should decode correctly', function() {
    assert.equal(duplicateEncode('din'), '(((');
    assert.equal(duplicateEncode("recede"),"()()()");
    assert.equal(duplicateEncode("Success"),")())())","should ignore case");
    assert.equal(duplicateEncode("(( @"),"))((");
  });
});
