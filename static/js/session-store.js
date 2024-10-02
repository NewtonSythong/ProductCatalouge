/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


export const sessionStore = Vuex.createStore({

    state() {
        // signed in customer
        customer: null;

        // the shopping cart items
        items: null;

        // selected product
        selectedProduct: null;

        // basic access authentication token
        authToken: null;

    },

    mutations: {

        // user signs in
        signIn(state, customer) {
            state.customer = customer;
            state.items = new Array();
        },

        // user selects a product
        selectProduct(state, product) {
            state.selectedProduct = product;
        },

        // add item to cart
        addItem(state, item) {
            state.items.push(item);
        },

        // clear cart items
        clearItems(state) {
            state.items = new Array();
        },

        // store basic access token
        authToken(state, token) {
            state.authToken = token;
        }
    },

    // add session storage persistence
    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});