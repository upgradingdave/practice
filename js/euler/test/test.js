import {assert} from 'chai';
import {main} from '../index';
import {sumDiagonals, firstFibWithLength, fib, reverse, 
        add} from '../src/euler';

describe('Logistics', function() {
  it('project configured correctly', 
     function () {
       assert.equal("Project Euler", main());
     });
});

describe('Adding Large Numbers', function() {
  it('should add numbers of any size', 
     function () {
       assert.equal('01', reverse('10'));

       assert.equal('3',     add('2', '1'));
       assert.equal('5',     add('3', '2'));
       assert.equal('10',    add('9', '1'));
       assert.equal('100',   add('99', '1'));
       assert.equal('1000',  add('999', '1'));

       assert.equal('1', fib(1));
       assert.equal('1', fib(2));
       assert.equal('2', fib(3));
       assert.equal('3', fib(4));
       assert.equal('5', fib(5));
       assert.equal('8', fib(6));
       assert.equal('13', fib(7));
       assert.equal('21', fib(7, '8', '13')[1]);
     });
});

describe('Euler Problems', function() {
  it('should find the first fibonacci number to contain 1000 digits', 
     function () {
       assert.deepEqual(12, firstFibWithLength(3));
       // problem 25
       //assert.deepEqual(4782, firstFibWithLength(1000));
     });
});

describe('number spirals', function() {
  it('should sum diagonals of number spirals', 
     function () {
       assert.deepEqual(101, sumDiagonals(5));
       assert.deepEqual(669171001, sumDiagonals(1001));
     });
});

