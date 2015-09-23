/**
 * Created by Ákos on 2015.09.23..
 */

var gulp = require('gulp');

var config = require('./gulp.config')();
var $ = require('gulp-load-plugins')({lazy: true});

gulp.task('hello-world', function () {
    console.log('Or first task.');
});

gulp.task('wiredep', function () {
    var options = config.getWiredepDefaultOptions();
    var wiredep = require('wiredep').stream;
    return gulp
        .src(config.index)
        .pipe(wiredep(options))
        .pipe($.inject(gulp.src(config.js)))
        .pipe(gulp.dest(config.client));
});