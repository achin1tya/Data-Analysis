
import * from "jsonwebtoken"


var signature=jsonwebtoken.sign({
  "username":"NSDA",
  "password":"0000"
}, 'secret', { expiresIn: '1h' });

document.getElementById("signature")=signature;