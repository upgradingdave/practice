var expect = require('chai').expect;
var assert = require('chai').assert;

const {traverseTCPStates} = require("../src/tcp_fsm.js");

describe('TCP Finite State Machine', function(done) {
  it('should calculate state', function() {
    assert.equal(traverseTCPStates(["APP_PASSIVE_OPEN", "APP_SEND",
				    "RCV_SYN_ACK"]),"ESTABLISHED");
    assert.equal(traverseTCPStates(["APP_ACTIVE_OPEN"]), "SYN_SENT");
    assert.equal(traverseTCPStates(["APP_ACTIVE_OPEN", "RCV_SYN_ACK",
				    "APP_CLOSE", "RCV_FIN_ACK", "RCV_ACK"]),
		 "ERROR");
    assert.equal(traverseTCPStates(["RCV_SYN", "RCV_ACK", "DNE"]), "ERROR");
  });
});
