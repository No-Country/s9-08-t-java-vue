import App from './App.vue'
import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { router } from './router/router'
import { registerRoute } from './register-routes'
import homeRouter from './modules/home/router'
import dashRouter from './modules/dashboard/router'
import authRouter from './modules/auth/router'
import movingRouter from './modules/moving/router'

const app = createApp(App)

registerRoute([dashRouter, homeRouter, authRouter, movingRouter])

app.use(createPinia())
app.use(router)

app.mount('#app')
