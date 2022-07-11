// import { createRouter } from "vue-router";
// const router = createRouter({
//   // ...
// });

// // ❌ Depending on the order of imports this will fail
// const store = useStore();

// router.beforeEach((to, from, next) => {
//   // we wanted to use the store here
//   if (store.isLoggedIn) next();
//   else next("/login");
// });

// router.beforeEach((to) => {
//   // ✅ This will work because the router starts its navigation after
//   // the router is installed and pinia will be installed too
//   const store = useStore();

//   if (to.meta.requiresAuth && !store.isLoggedIn) return "/login";
// });
