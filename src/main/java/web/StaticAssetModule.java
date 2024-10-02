package web;

import io.jooby.Jooby;
import io.jooby.Route;
import java.nio.file.Paths;

public class StaticAssetModule extends Jooby {

    public StaticAssetModule() {

        // handle favicons (silent 404)
        get("/favicon.ico", Route.FAVICON);

        // serve anything that matches a file in the static folder
        assets("/*", Paths.get("static"));

        // home page
        assets("/", Paths.get("static/index.html"));

        // html files
        assets("/index.html", Paths.get("static/index.html"));
        assets("/500.html", Paths.get("static/500.html"));
        assets("/cart.html", Paths.get("static/cart.html"));
        assets("/create-account.html", Paths.get("static/create-account.html"));
        assets("/order-confirmation.html", Paths.get("static/order-confirmation.html"));
        assets("/quantity.html", Paths.get("static/quantity.html"));
        assets("/sign-in.html", Paths.get("static/sign-in.html"));
        assets("/view-products.html", Paths.get("static/view-products.html"));

        // css files
        assets("/css/style.css", Paths.get("static/css/style.css"));
        // ... and the rest of the CSS files

        // JavaScript files
        assets("/js/session-store.js", Paths.get("static/js/session-store.js"));
        assets("/js/index.js", Paths.get("static/js/index.js"));
        assets("/js/navigation-menu.js", Paths.get("static/js/navigation-menu.js"));
        assets("/js/cart.js", Paths.get("static/js/cart.js"));
        assets("/js/create-account.js", Paths.get("static/js/create-account.js"));
        assets("/js/number-formatter.js", Paths.get("static/js/number-formatter.js"));
        assets("/js/sign-in.js", Paths.get("static/js/sign-in.js"));
        assets("/js/view-products.js", Paths.get("static/js/view-products.js"));
        // ... and the rest of the JavaScript files

        // external JavaScript files
        assets("/js/external/vue.global.js", Paths.get("static/js/external/vue.global.js"));
        assets("/js/external/vuex.global.js", Paths.get("static/js/external/vuex.global.js"));
        assets("/js/external/vuex-persistedstate.js", Paths.get("static/js/external/vuex-persistedstate.js"));
        assets("/js/external/axios.js", Paths.get("static/js/external/axios.js"));

        // ... and any images that you have added, if any
    }
}
