import "./assets/blog.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import VCalendar from "v-calendar";
import VueTyperPlugin from 'vue-typer'
import "v-calendar/style.css";

const app = createApp(App);
const pinia = createPinia();

pinia.use(piniaPluginPersistedstate);
app.use(router).use(pinia).use(VCalendar, {}).use(VueTyperPlugin);

app.mount("#app");
