var expect = require('chai').expect;
var assert = require('chai').assert;

const {toGoatLatin} = require("../src/goatlatin.js");

describe("GoatLatin", function(done) {

  it('converts string to goat latin', function() {
    expect(toGoatLatin("I speak Goat Latin"))
      .to.equal("Imaa peaksmaaa oatGmaaaa atinLmaaaaa");

    expect(toGoatLatin("The quick brown fox jumped over the lazy dog"))
      .to.equal("heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa");

    expect(toGoatLatin("Each word consists of lowercase and uppercase letters only"))
      .to.equal("Eachmaa ordwmaaa onsistscmaaaa ofmaaaaa owercaselmaaaaaa andmaaaaaaa uppercasemaaaaaaaa etterslmaaaaaaaaa onlymaaaaaaaaaa");
    
  });

});
