var express = require('express');
//var cookieParser = require('cookie-parser');
var session = require('express-session');
const uuid = require('uuid/v4')
const bodyParser = require("body-parser");
var app = express();

//app.use(cookieParser());

app.use(session(
    {
        genid: function(req) {
            return uuid
        },
        name: 'SESS_ID',
        secret: "Shh, its a secret!",
        resave: true,
        saveUninitialized: true
    }
));

app.use(bodyParser.urlencoded({
    extended: true
}));

app.use((req, res, next) => {
    if (req.session.csrfToken === undefined) {
        req.session.csrfToken = uuid()
    }
    console.log("req.session.csrfToken :" + req.session.csrfToken);
    next();
})

app.get('/', function(req, res){
    res.sendFile('index.html', {root: __dirname })
});

app.post('/getcsrf', function(req, res){
    console.log("Sent CSRF " + req.session.csrfToken);
    res.json(
        { 
            csrfToken: req.session.csrfToken 
        });
});

app.post('/transfer', function(req, res, next){
    console.log("transfer post");
    console.log("req.session.csrfToken " + req.session.csrfToken);
    console.log("req.body.csrfToken " + req.body.csrfToken);
    if (req.session.csrfToken !== req.body.csrfToken) {
        console.log('Invalid CSRF Token!');
        let err = new Error('Invalid CSRF Token!')
        err.status = 403
        return next(err)
    } else {
        res.sendFile('sucess.html', {root: __dirname })
    }
});

app.listen(3000, () => {
    console.log(`Listening on http://localhost:3000`);
});