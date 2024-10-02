/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


/* global Vue, axios*/

var customer = ({username}) => `/api/customers/${username}`;


const app = Vue.createApp({

    data() {
        return{
            customer: new Object(),
            signedInMsg: "Please sign in to continue"
        };
    },
    mounted() {

    },
    methods: {
        signIn() {
            this.createToken(this.customer.username, this.customer.password);
            axios.get(customer({'username': this.customer.username}))
                .then(response => {
                    this.customer = response.data;
                    sessionStore.commit("signIn", this.customer);
                    window.location = 'index.html';
                })
                .catch(error => {
                    console.error("There was an error signing in:", error);
                });
        }
    },
    // other modules
    mixins: [BasicAccessAuthentication]
});

//imports 
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import session store
import { sessionStore } from './session-store.js';
app.use(sessionStore);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");