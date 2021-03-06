//import './home.css';

import angular from 'angular';
import uirouter from 'angular-ui-router';

import loading from '../loading/loading';
import apis from '../apis/apis';

import homeRoute from './home-route';
homeRoute.$inject = ['$stateProvider'];
import homeController from './home-controller';
homeController.$inject = ['$log', '$state', 'loadingService', 'apisService'];

/**
 * definition of angular home module
 */
export default angular.module('budgeto.home', [uirouter, loading, apis])
    .controller('homeController', homeController)
    .config(homeRoute)
    .name;
