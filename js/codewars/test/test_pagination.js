var expect = require('chai').expect;
var assert = require('chai').assert;

const {PaginationHelper} = require("../src/pagination.js");

describe('PaginationHelper', function(done) {
  it('uses math correctly', function() {
    (assert.equal(Math.floor(10 / 3), 3));
  });
  it('describe page counnt', function() {
    let helper = new PaginationHelper(['a','b','c','d','e','f'], 4);
    assert.equal(helper.pageCount(), 2);
    assert.equal(helper.itemCount(), 6);
    assert.equal(helper.pageItemCount(0), 4);
    assert.equal(helper.pageItemCount(1), 2);
    assert.equal(helper.pageItemCount(2), -1);

    assert.equal(helper.pageIndex(5), 1);
    assert.equal(helper.pageIndex(2), 0);
    assert.equal(helper.pageIndex(20), -1);
    assert.equal(helper.pageIndex(-10), -1);
  });
});
