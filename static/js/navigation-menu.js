/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

"use strict";

export const navigationMenu = {

    computed: {
        signedIn() {
            return this.customer != null;
        },
        ...Vuex.mapState({
                customer: 'customer'
        })
    },

    template:
            `
    <nav>
        <a style="float: right;" v-if="signedIn">Welcome {{customer.firstName}}</a> 
        <a href=".">Home</a>
        <a href="view-products.html" v-if="signedIn">Browse Products</a>
        <a href="cart.html" v-if="signedIn">View Cart</a>
        <a href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
        <a href="sign-in.html" v-if="!signedIn">Sign in</a>
        <a href="create-account.html" v-if="!signedIn">Create a new Account</a>


    </nav>
    `,

    methods: {
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }
    }
};  