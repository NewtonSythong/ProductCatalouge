/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

//import number formatter
import { NumberFormatter } from './number-formatter.js';

/* global Vue, axios*/
var productsApi = '/api/products';
var categoriesApi = '/api/categories';
var categoriesFilterApi = ({ category }) => `/api/categories/${category}`;

const app = Vue.createApp({
    data() {
        return {
            products: [],
            categories: []
        };
    },
    mounted() {
        this.getProducts();
        this.getCategories();
    },
    methods: {
        getProducts() {
            axios.get(productsApi)
                    .then(response => {
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error('Error fetching products:', error.response ? error.response.data : error.message);
                        alert("Error fetching products! Check console for details");
                    });
        },
        getCategories() {
            axios.get(categoriesApi)
                    .then(response => {
                        this.categories = response.data;
                    })
                    .catch(error => {
                        console.error('Error fetching categories:', error.response ? error.response.data : error.message);
                        alert("Error fetching categories! Check console for details");
                    });
        },
        filterByCategory(category) {
            axios.get(categoriesFilterApi({category}))
                    .then(response => {
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error('Error filtering products:', error.response ? error.response.data : error.message);
                        alert("Error filtering products! Check console for details");
                    });
        },
        buyProduct(product) {
            sessionStore.commit("selectProduct", product);
            window.location = "quantity.html";
        }
    },

    // other modules
    mixins: [NumberFormatter, BasicAccessAuthentication]
});

//imports 

import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// import session store
import { sessionStore } from './session-store.js';
app.use(sessionStore);


// mount the page - this needs to be the last line in the file
app.mount("main");