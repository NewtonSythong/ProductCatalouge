/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


/* global Vue, axios*/

var customersApi = '/api/register';
const app = Vue.createApp({

    data() {
        return{
            customer: new Object()
        };
    },
    mounted() {

    },
    methods: {
        createAccount() {
            axios.post(customersApi, this.customer)
                    .then(() => {
                        window.location = 'index.html';
                    })
                    .catch(error => {
                        alert(error.response.data.message);
                        return ctx.send(StatusCode.BAD_REQUEST);
                    });
        }
    },

    // other modules
    mixins: []

});
// other component imports go here
// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import data store
import { sessionStore } from './session-store.js'
app.use(sessionStore);


// mount the page - this needs to be the last line in the file
app.mount("main");