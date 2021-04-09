import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            alias: "/authors",
            name: "authors",
            component: () => import("./components/Authors")
        },
        // {
        //     path: "/tutorials/:id",
        //     name: "tutorial-details",
        //     component: () => import("./components/Tutorial")
        // },
        {
            path: "/addAuthor",
            name: "addAuthor",
            component: () => import("./components/AuthorsForm")
        },
        {
            path: "/books",
            name: "books",
            component: () => import("./components/Books")
        },
    ]
});