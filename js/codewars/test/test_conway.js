var expect = require('chai').expect;
var assert = require('chai').assert;

const {getGeneration,
       neighbors,
       grow,
       shrink} = require("../src/conway.js");

describe("Conway's Game of Life", function(done) {

  let gliders = [
    [[1,0,0],
     [0,1,1],
     [1,1,0]],
    [[0,1,0],
     [0,0,1],
     [1,1,1]]
  ];

  it('finds neighbors', function() {
    assert.deepEqual(neighbors(gliders[0], 0, 0), [0, 0, 1]);
  });

  it('can grow', function() {
    assert.deepEqual(grow(gliders[0]), [[0,0,0,0,0],
					[0,1,0,0,0],
					[0,0,1,1,0],
					[0,1,1,0,0],
					[0,0,0,0,0]]);
    assert.deepEqual(grow([[0,1,1,0],
			   [1,1,1,1]]),
		     [[0,0,0,0,0,0],
		      [0,0,1,1,0,0],
		      [0,1,1,1,1,0],
		      [0,0,0,0,0,0]]);
  });

  it('can shrink', function() {
    assert.deepEqual(shrink([[0]]), [[]]);
    assert.deepEqual(shrink([]), [[]]);
    assert.deepEqual(shrink([[]]), [[]]);
    assert.deepEqual(shrink([[0,1,0]]), [[1]]);
    assert.deepEqual(shrink([[0,1,0],
			     [0,1,0]]), [[1],
					 [1]]);
    assert.deepEqual(shrink([[0,1,0],
			     [0,1,1]]), [[1,0],
					 [1,1]]);

    assert.deepEqual(shrink([[0,0,0],
			     [0,0,0]]), [[]]);

    assert.deepEqual(shrink([[0,0,1,0],
			     [0,0,1,0]]), [[1],[1]]);
    
    
    //assert.deepEqual(shrink(grow(gliders[0])), grow(gliders[0]));
    /* assert.deepEqual(grow([[0,1,1,0],
       [1,1,1,1]]),
       [[0,0,0,0,0,0],
       [0,0,1,1,0,0],
       [0,1,1,1,1,0],
       [0,0,0,0,0,0]]);*/
  });
  
  it('getGeneration', function() {    
    assert.deepEqual(getGeneration(gliders[0], 1), gliders[1]);
  });

});
