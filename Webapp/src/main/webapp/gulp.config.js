/**
 * Created by Ákos on 2015.09.23..
 */


module.exports = function () {
    var client = './';
    var clientApp = client + 'app';

    console.log(clientApp);

    var config = {
        client: client,
        temp: './.tmp/',
        alljs: [],
        js: [
            clientApp + '/**/app.js',
            clientApp + '/**/*.js',
            '!' + clientApp + '/**/*.spec.js',
        ],
        index: client + 'index.jsp',

        bower: {
            json: require('./bower.json'),
            directory: './vendor/bower/',
            ignorePath: '../..'
        }

    };

    config.getWiredepDefaultOptions = function () {
        var options = {
            bowerJson: config.bower.json,
            directory: config.bower.directory,
            ignorePath: config.bower.ignorePath

        };
        return options;
    };

    return config;
}