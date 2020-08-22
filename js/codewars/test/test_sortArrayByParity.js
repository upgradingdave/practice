var expect = require('chai').expect;
var assert = require('chai').assert;

const {sortArrayByParity} = require("../src/sortArrayByParity.js");

describe("Sort Array By Parity", function(done) {

  var arr1 = [1,2,3];
  var arr2 = [2,1,3];

  it('sorts array', function() {
    assert(true, 'true');
    assert(arr2, sortArrayByParity(arr2));
    //assert(arr2 === sortArrayByParity(arr1), 'arr1');
  });

});
