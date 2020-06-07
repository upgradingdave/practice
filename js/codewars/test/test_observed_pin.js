var expect = require('chai').expect;
var assert = require('chai').assert;

const {getPINs} = require("../src/observed_pin.js");

describe('Observed PIN', function(done) {
  it('finds all possible pins', function() {
    assert.deepEqual(getPINs('8').sort(), ['5', '7', '8', '9', '0'].sort());

    assert.deepEqual(
      getPINs('11').sort(),
      ['11', '22', '44', '12', '21', '14', '41', '24', '42'].sort());

    assert.deepEqual(getPINs("369").sort(), ["339","366","399","658","636","258","268","669","668","266","369","398","256","296","259","368","638","396","238","356","659","639","666","359","336","299","338","696","269","358","656","698","699","298","236","239"].sort());

  });
});
