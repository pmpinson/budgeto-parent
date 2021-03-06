var helper = require('./helper');
var _ = require('lodash');

var accounts = function (req) {
    var base = helper.baseUrl(req);
    return [
        {
            name: 'livretA',
            note: 'The livret A'
            ,
            operations: [{date: '2015-10-11T18:55:30', label: 'ope1'}, {date: '2015-10-12T15:55:30', label: 'ope2'}]
            ,
            links: [{rel: 'self', href: base + 'account/livretA'}, {
                rel: 'operations',
                href: base + 'account/livretA/operations'
            }]
        },
        {
            name: 'Cheque',
            note: 'global account'
            ,
            operations: []
            ,
            links: [{rel: 'self', href: base + 'account/Cheque'}, {
                rel: 'operations',
                href: base + 'account/Cheque/operations'
            }]
        }
    ];
};

/* GET mocks account. */
module.exports = function (router) {
    router.get('/account', function (req, res, next) {
        res.json(accounts(req));
    });
    router.get('/account/:acount_name', function (req, res, next) {
        console.log(req.params.acount_name, _.find(accounts(req), {name: req.params.acount_name}));
        res.json(_.find(accounts(req), {name: req.params.acount_name}));
    });
    router.get('/account/:acount_name/operations', function (req, res, next) {
        console.log(req.params.acount_name, _.find(accounts(req), {name: req.params.acount_name}));
        res.json(_.find(accounts(req), {name: req.params.acount_name}).operations);
    });
};
