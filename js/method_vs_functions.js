// Javascript uses 4 patterns of invoking a function. These all differ
// in the way that `this` is treated.

// 1. method invocation - When a function is stored as a property of
// an object, it's referred to as a "method" and `this` is bound to
// the object.

var person = {name: "Dave", 
              setName: function(name) { 
                // `this` refers to object
                this.name = name; 
              }
             }

    // Good thing that we bound the value of 'this' from the 'setName'
    // method to a new variable named 'that' earlier, because now we
    // can use 'that' to access the thing that you probably think that
    // 'this' should actually refer to!

// > person.name
// "Dave"
// > person.setName("Tim");
// > person.name
// "Tim"

// 2. function invocation - If a function is defined outside of the
// context of an object, then `this` is bound to the global object.
// This is a strange part of the language and so I don't think `this`
// is used inside functions very often.

var myFunction = function() {
  // `this` bound to global object
  this.isnt_very_useful = "hello";
};

// Things get weird when you define a function inside a method.

person.setName = function(name) {

  var that = this;
  
  var setDefaultName = function() {
    // `this` refers to global object
    this.name = "this isn't what we want";
    
    // since we bound the value of method `this` to `that`, we can use
    // `that` as a workaround
    that.name = "Fred";
  }

  if (name == undefined) 
    setDefaultName();
};

// > person.setName();
// > person.name
// "Fred"
// > name
// "this isn't what we want"

// 3. constructor invocation - if a function is called with the `new`
// prefix, a new object is created with a link to the function's
// prototype and `this` is assigned to the new object. If you forget
// to use `new`, bad things happen. Constructor invocation isn't
// recommended. 

var Person = function (name) {
  this.name = name;
};

Person.prototype.getName = function() { return this.name };

// > var person = new Person("Dave");
// > person.getName();
// "Dave"
// > var person = Person("Dave");
// > person.getName();
// Uncaught TypeError: Cannot read property 'getName' of undefined

// 3. apply invocation - use `apply` to manually call functions and
// set `this` to whatever you want.

var cat = {"name": "shubie"};

// even though our cat object doesn't have a "getName" function
// defined, we can reuse the getName from Person object on a cat like
// so: 

// > Person.prototype.getName.apply(cat);
// "shubie"

