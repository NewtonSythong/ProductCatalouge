/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


export const NumberFormatter = {
    methods: {
        formatCurrency(value) {
            return Intl.NumberFormat('en-NZ', {style: "currency", currency: 'NZD'}).format(value);
        },
        formatNumber(value, decimalPlaces) {
            // cast to number in case  value is a string
            let number = Number(value);

            return number.toFixed(decimalPlaces);
        }
    }
}