import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            alias: "/home",
            name: "home",
            component: () => import("./components/Home")
        },
        {
            path: "/authors",
            name: "authors",
            component: () => import("./components/Authors")
        },
        {
            path: "/addAuthor",
            name: "addAuthor",
            component: () => import("./components/AuthorsForm")
        },
        {
            path: "/authors/:id",
            name: "authors",
            component: () => import("./components/AuthorsEditForm")
        },
        {
            path: "/books",
            name: "books",
            component: () => import("./components/Books")
        },
    ]
});