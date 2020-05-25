'use strict';

let logEnabled = false;

function log(msg) {
  if(logEnabled==true) {
    console.log(msg);
  }
}

function p(name, val) {
  log(">>> "+ name + ": "+ val);
}

export function reverse(n) {
  let i = n.length-1;
  let result = "";
  while(i>=0) {
    result += n[i]
    i=i-1;
  }
  return result;
}

/**
 * Adds two numbers of any size together. `a` should always be larger
 * than `b`.
 */
export function add(a, b) {
  //logEnabled = false;
  let a1 = ""+a
  let b1 = ""+b

  let result = "";
  let left = 0;

  let a_idx = a1.length-1;
  let b_idx = b1.length-1;
  
  while(a_idx >= 0) {

    p("a_idx", a_idx);

    let x = parseInt(a1.charAt(a_idx));
    let y = 0;

    if (b_idx >= 0) {
      y = parseInt(b1.charAt(b_idx));
    }

    p("x", x);
    p("y", y);
    p("left", left);

    let r = "" + (x + y + left);

    p("r", r);

    if(r.length > 1) {
      left = parseInt(r.charAt(0));
      result += r.charAt(1);
    } else {
      left = 0;
      result += r.charAt(0);
    }

    a_idx=a_idx-1;
    b_idx=b_idx-1;
  }

  if (left > 0) {
    result+= left;
  }
  //logEnabled = true;
  return reverse(result);
}

export function fib(n, r1, r2) {
//  logEnabled = true;
  if(n<=2) {
    return '1';
  }

  let i = 3;
  let incremental = false;
  if(r1==undefined && r2==undefined) {
    r1 = 1;
    r2 = 1;
  } else {
    incremental = true;
    i=n;
    r1=r1;
    r2=r2;
  }

  while(i<=n) {

    p("i", i);
    p("r1", r1);
    p("r2", r2);
    
    let t = r2;
    r2 = add(r2, r1);
    r1 = t;
    i = i+1;
  }
  //logEnabled = false;
  if(incremental) {
    return [r1, r2];
  } else {
    return r2;
  }
}

/**
 * Find the index in the fibonacci sequence where the length of the 
 * value is greater than len digits
 */
export function firstFibWithLength(len) {
  let i = 3;
  var [r1,r2] = fib(i, '1', '1');
  while(r2.length < len) {
    [r1,r2] = fib(i, r1, r2);
    i = i + 1;
  }
  return i;
}

export function sumDiagonals(n) {
  //logEnabled = true;
  let max = n * n;
  let total = 1;
  let l = 2;
  let i = 3;
  let g = 2;
  while (i <= max) {
    let j = i;
    for(let x = 0; x < 4; x++) {
      p("j", j);
      total = total + j;
      j = j + l;
    }
    l = l + 2;
    g = g + 8;
    i = i + g;
  }
  return total;
}
