import App from './App.vue'
import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { router } from './router/router'
import { registerRoute } from './register-routes'
import { plugin, defaultConfig } from '@formkit/vue'
import config from './../formkit.config'
import homeRouter from './modules/home/router'
import dashRouter from './modules/Dashboard/router'
import authRouter from './modules/auth/router'

const app = createApp(App)

registerRoute([dashRouter, homeRouter, authRouter])

app.use(createPinia())
app.use(router)
app.use(plugin, defaultConfig(config))

app.mount('#app')
