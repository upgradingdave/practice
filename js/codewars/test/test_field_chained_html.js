var expect = require('chai').expect;
var assert = require('chai').assert;

const {Format} = require("../src/field_chained_html.js");

describe('Field Chained Html', function(done) {
  it('should output correct html', function() {
    var wrapInDiv = Format.div;
    assert.equal(wrapInDiv('Foo'), "<div>Foo</div>");
    assert.equal(wrapInDiv.p('Bar'), "<div><p>Bar</p></div>");

    var wrapInDivH1 = Format.div.h1;
    assert.equal(wrapInDivH1("Far"),"<div><h1>Far</h1></div>");
    assert.equal(wrapInDivH1.span("Bar"),
		 "<div><h1><span>Bar</span></h1></div>");
  });
});

