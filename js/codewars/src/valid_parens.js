function validParentheses(parens){
  if(parens == undefined || parens.length < 0) return true;
  let p = 0;
  for(let i=0; i<parens.length; i++) {
    let c = parens.charAt(i);
    if( p == 0 && c == ')' ) return false;
    p = ( c == '(' ) ? p + 1 : p - 1;
  }
  return p == 0;
}

module.exports = {
  validParentheses: validParentheses
}

