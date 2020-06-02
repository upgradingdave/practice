var expect = require('chai').expect;
var assert = require('chai').assert;

const {validParentheses} = require("../src/valid_parens.js");

describe('Valid Parenthesis?', function(done) {
  it('Basic tests', function() {
    assert.equal(validParentheses( "()" ), true);
    assert.equal(validParentheses( "())" ), false);
    assert.equal(validParentheses( ")(()))" ), false);
    assert.equal(validParentheses( "(" ), false);
    assert.equal(validParentheses( "(())((()())())" ), true);
    assert.equal(validParentheses( ")()()()(" ), false);
  });
});
