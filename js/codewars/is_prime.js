
function isPrime(num){
  if(num < 2) return false;
  if(num != 2 && num % 2 == 0) return false;
  if(num != 3 && num % 3 == 0) return false;
  
  for(let i=3; i <= Math.sqrt(num); i++) {
    if(num % i == 0) return false;
  }
  return true;
}

module.exports = {
  isPrime: isPrime
}

