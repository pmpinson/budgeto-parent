/**
 * provider to manage definition of apis
 */
class ApisService {

    constructor($log, $resource, budgetoRestApiURL) {
        this.$log = $log;
        this.$resource = $resource;
        this.budgetoRestApiURL = budgetoRestApiURL;
        this.apis = [];
    }

    /**
     * load apis definition
     * @returns {*}
     */
    load() {
        this.$log.debug('ApisService', 'call api to get all available apis', this.budgetoRestApiURL);

        var self = this;

        return this.$resource(this.budgetoRestApiURL, {}, {}).get({}).$promise.then(function(data) {
            self.loadApis(data);

            return data;
        });
    }

    /**
     * load result of apis definitions
     * @param data
     */
    loadApis(data) {
        for (var key in data.links) {
            if (data.links[key].rel !== 'self') {
                this.apis.push(data.links[key]);
            }
        }
        this.$log.debug('ApisService', 'available apis', this.apis);
    }

    /**
     * get all apis
     * @returns {Array}
     */
    all() {
        return this.apis;
    }

    /**
     * get one api by is name
     * @param rel
     * @returns {*}
     */
    get(rel) {
        return this.getLink(rel, this.apis);
    }

    /**
     * get link of an api by name
     * @param rel
     * @param links
     * @returns {*}
     */
    getLink(rel, links) {
        for (var key in links) {
            if (links[key].rel === rel) {
                return links[key];
            }
        }

        return null;
    }
}

export default ApisService;
