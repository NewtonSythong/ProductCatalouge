/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


"use strict";

class SaleItem {
    constructor(product, quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = product.listPrice;
    }
}

class Sale {
    constructor(customer, items) {
        this.customer = customer;
        this.items = items;
    }
}

var salesApi = '/api/sales';
var total = 0;

const app = Vue.createApp({

    data() {
        return {
// models (comma separated key/value pairs)

        };
    },
    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer'
    }),
    
    mounted() {
// semicolon separated statements


    },
    methods: {
        // comma separated function declarations
        checkOut() {
            let sale = new Sale(this.customer, this.items);
            console.log();
            axios.post(salesApi, sale)
                    .then(() => {
                        sessionStore.commit("clearItems");
                        window.location = 'order-confirmation.html';
                    })
                    .catch(error => {
                        alert(error.response.data.message);
                    });
        },
        addProductToCart() {
            sessionStore.commit("addItem", new SaleItem(this.product, this.quantity));
            window.location = "view-products.html";
        },
        getItemTotal(item) {
            var itemTotal = item.salePrice * item.quantityPurchased;
            total += itemTotal;
            return itemTotal;
        },
        getTotal() {
            return total;
        },
        isNumber: function (evt) {
            evt = (evt) ? evt : window.event;
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode !== 46) {
                evt.preventDefault();
            } else {
                return true;
            }
        },
        isEmpty: function (evt) {
            if (evt.target.value === '' || evt.target.value.charAt(0) === '0') {
                this.quantity = 1;
            }
            if (evt.target.value > this.product.quantityInStock) {
                this.quantity = this.product.quantityInStock;
            }
        },

    },
// other modules
    mixins: [NumberFormatter, BasicAccessAuthentication]


});

/* other component imports go here */

// import data store

// import navigation  menu component
import { navigationMenu } from './navigation-menu.js';
app.component('navmenu', navigationMenu);


import { sessionStore } from './session-store.js';
app.use(sessionStore);

//import number formatter
import { NumberFormatter } from './number-formatter.js';

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");